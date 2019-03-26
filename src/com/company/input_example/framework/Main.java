package com.company.input_example.framework;

import com.company.input_example.company.CompanyInfo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    /**
     * 1. Console input of an object by object's fields
     * 2. Generating Employee object and passing it into save()
     * 3. Calling @Save
     */

    public static void main(String[] args) throws Exception {

        Class mainClass = Class.forName("com.company.input_example.company.CompanyInfo");


        //@Save
        //public void save( ... )
        Method saveMethod = Arrays.stream(mainClass.getMethods())
                .filter(x -> x.isAnnotationPresent(Save.class))
                .findFirst()
                .get();

        //public void save( String a, int b, @Input Person person )
        Parameter[] parameters = saveMethod.getParameters();
        Parameter inputParameter = Arrays.stream(parameters)    //@Input Employee employee
                .filter(x -> x.isAnnotationPresent(Input.class))
                .findFirst()
                .get();

        Class inputType = inputParameter.getType(); //Employee.class
        //Class inputType = inputParameter.getClass(); //Parameter.class

        //Field []
        List<Field> inputTypesFields =
                Arrays
                        .stream(inputType.getDeclaredFields())
                        .filter(x -> x.isAnnotationPresent(InputField.class))
                        .collect(Collectors.toList());

        //new CompanyInfo
        Object mainClassInstance = mainClass.newInstance();


        //--------------- while true ----------------

        while (true) {

            //Employee
            Object inputObject = inputType.newInstance(); //new Employee

            for (Field field : inputTypesFields) {
                InputField inputField = field.getAnnotation(InputField.class);
                System.out.println(inputField.inputText());

                if (field.getType().equals(String.class)) { //employee.name = from console
                    String inputData = new Scanner(System.in).nextLine();
                    field.setAccessible(true);
                    field.set(inputObject, inputData);
                }

                if (field.getType().equals(Double.class)) { //employee.salary = from console
                    Double inputData = new Scanner(System.in).nextDouble();
                    field.setAccessible(true);
                    field.set(inputObject, inputData);
                }
            }

            saveMethod.invoke(mainClassInstance, inputObject); //companyInfo.save(employee)

            Method getAll = Arrays.stream(mainClass.getMethods())
                    .filter(x -> x.isAnnotationPresent(GetAll.class))
                    .findFirst()
                    .get();

            Object returnData = getAll.invoke(mainClassInstance); //companyInfo.getAll()
            System.out.println("Data to send to server: " + returnData.toString());
        }
    }


}
