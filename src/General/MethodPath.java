package General;

import java.util.*;

public class MethodPath {

    public List<List<String>> findPath(String startMethodName, String endMethodName, List<ClassTypeInfo> classes){

        List<List<String>> finallist = new ArrayList<>();
        List<String> currentlist = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for(ClassTypeInfo classObject:classes){
            List<MethodTypeInfo> methodsPresent = classObject.methodsPresent;
            for(MethodTypeInfo methods:methodsPresent){
                if(methods.methodTypeInfoName.equalsIgnoreCase(startMethodName)) {
                    currentlist.add(methods.methodTypeInfoName);
                    DFSMethods(startMethodName, endMethodName, methods, finallist, currentlist, set);
                }
            }
        }
        return finallist;
    }

    public void DFSMethods(String currentMethodName, String endMethodName, MethodTypeInfo currentMethod, List<List<String>> finalList, List<String> currentlist, Set<String> visited){
        visited.add(currentMethodName);

        if(currentMethodName.equalsIgnoreCase(endMethodName)) {
            finalList.add(currentlist);
            return;
        }

        if(currentMethod.calledMethods == null) return;

        for(MethodTypeInfo curMethod: currentMethod.calledMethods){
            currentlist.add(curMethod.methodTypeInfoName);
            if(!visited.contains(curMethod.methodTypeInfoName))
                DFSMethods(curMethod.methodTypeInfoName,endMethodName,curMethod,finalList, currentlist,visited);
        }
    }


    public static void main(String [] args){

        MethodTypeInfo m1 = new MethodTypeInfo("a1",null);
        MethodTypeInfo m2 = new MethodTypeInfo("a2",null);
        MethodTypeInfo m3 = new MethodTypeInfo("a3",null);
        List<MethodTypeInfo> methods1 = Arrays.asList(m1,m2,m3);
        MethodTypeInfo n1 = new MethodTypeInfo("b1",null);
        MethodTypeInfo n2 = new MethodTypeInfo("b2",null);
        MethodTypeInfo n3 = new MethodTypeInfo("b3",null);
        List<MethodTypeInfo> methods2 = Arrays.asList(n1,n2,n3);
        MethodTypeInfo o1 = new MethodTypeInfo("c1",null);
        MethodTypeInfo o2 = new MethodTypeInfo("c2",null);
        MethodTypeInfo o3 = new MethodTypeInfo("c3",null);
        List<MethodTypeInfo> methods3 = Arrays.asList(o1,o2,o3);
        m1.calledMethods = Arrays.asList(n1);
        m2.calledMethods = Arrays.asList(n2);
        m3.calledMethods = Arrays.asList(n3);
        n1.calledMethods = Arrays.asList(o1);
        n2.calledMethods = Arrays.asList(o2);
        n3.calledMethods = Arrays.asList(o3);
        ClassTypeInfo c1 = new ClassTypeInfo("class1",methods1);
        ClassTypeInfo c2 = new ClassTypeInfo("class2",methods2);
        ClassTypeInfo c3 = new ClassTypeInfo("class3",methods3);
        List<ClassTypeInfo> classes = new ArrayList<>();
        classes.addAll(Arrays.asList(c1,c2,c3));

        List<List<String>> result = new MethodPath().findPath("a3","c3",classes);
        if(result.isEmpty())
            System.out.print("No Path Found");
        for(List<String> res:result){
            System.out.print(Arrays.toString(res.toArray() )+ " ");
        }
    }
}

class MethodTypeInfo{
    String methodTypeInfoName;
    List<MethodTypeInfo> calledMethods;
    public MethodTypeInfo(String methodTypeInfoName, List<MethodTypeInfo> calledMethods){
        this.methodTypeInfoName = methodTypeInfoName;
        this.calledMethods = calledMethods;
    }
}

class ClassTypeInfo{
    String classTypeInfoName;
    List<MethodTypeInfo> methodsPresent;

    public ClassTypeInfo(String classTypeInfoName, List<MethodTypeInfo> methodsPresent){
        this.classTypeInfoName = classTypeInfoName;
        this.methodsPresent = methodsPresent;
    }
}