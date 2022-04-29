package General;
import java.io.*;
import java.util.*;

/*
 * This class will only be used as a singleton object
 */
public class JelliInterview
{
    // add any members you need
    private Map<Integer, Person> personsMap;
    private Map<Integer, List<Person>> departmentMap;

    private static volatile JelliInterview obj = null;

    private JelliInterview( List<Person> personList )
    {
        this.personsMap = new HashMap<>();
        this.departmentMap = new HashMap<>();
        for(int i=0; i< personList.size(); i++){
            Person person = personList.get(i);
            personsMap.put(person.getId(), person);
            int departmentId = person.getDepartment().getId();
            departmentMap.computeIfAbsent(departmentId, k-> new ArrayList<>()).add(person);
        }
        // complete this constructor
    }

    public JelliInterview getInstance(List<Person> personList){
        if(obj==null){
            synchronized(JelliInterview.class){
                if(obj==null){
                    obj = new JelliInterview(personList);
                }
            }
        }
        return obj;
    }

    public List<Person> getPersonsByDepartmentId( Integer departmentId )
    {
        // find people by department id
        if(departmentId==null) return new ArrayList<>();
        return departmentMap.getOrDefault(departmentId, new ArrayList<>());

    }

    public Person getPersonById( Integer personId )
    {
        // return the person with the provided id
        if(personId==null)return null;
        return personsMap.getOrDefault(personId, null);

    }

    public Integer addPerson(Person person){
        if(person==null) throw new IllegalArgumentException("Invalid Person");
        int departmentId = -1;
        if(!personsMap.containsKey(person.getId())){
            try {
                departmentId = person.getDepartment().getId();
                personsMap.put(person.getId(), person);
                departmentMap.computeIfAbsent(departmentId, k -> new ArrayList<>()).add(person);
            }
            catch(Exception e){
                e.printStackTrace();
                return -1;
            }
            finally {
                if(!(personsMap.containsKey(person.getId()) && departmentMap.containsKey(departmentId))){
                    personsMap.remove(person.getId());
                    departmentMap.remove(departmentId);
                }

            }
        }
        return person.getId();
    }
}

//**************************************************
 class Person
{

    private Integer id;
    private String firstName;
    private String middleName;
    private String lastName;

    private Department department;

    public Integer getId()
    {
        return id;
    }

    public void setId( Integer id )
    {
        this.id = id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName( String firstName )
    {
        this.firstName = firstName;
    }

    public String getMiddleName()
    {
        return middleName;
    }

    public void setMiddleName( String middleName )
    {
        this.middleName = middleName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName( String lastName )
    {
        this.lastName = lastName;
    }

    public Department getDepartment()
    {
        return department;
    }

    public void setDepartment( Department department )
    {
        this.department = department;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode( this.id );
        return hash;
    }

    @Override
    public boolean equals( Object obj )
    {
        if ( this == obj )
        {
            return true;
        }
        if ( obj == null )
        {
            return false;
        }
        if ( getClass() != obj.getClass() )
        {
            return false;
        }
        final Person other = (Person) obj;
        if ( !Objects.equals( this.id, other.id ) )
        {
            return false;
        }
        return true;
    }

}

class Department
{

    private Integer id;
    private String name;

    public Integer getId()
    {
        return id;
    }

    public void setId( Integer id )
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 23 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals( Object obj )
    {
        if ( this == obj )
        {
            return true;
        }
        if ( obj == null )
        {
            return false;
        }
        if ( getClass() != obj.getClass() )
        {
            return false;
        }
        final Department other = (Department) obj;
        if ( this.id != other.id )
        {
            return false;
        }
        return true;
    }

}

