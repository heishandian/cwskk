package com.yaoli.ajob.designPattern;


/**
 * Created by will on 2016/12/8.
 * 建造者模式
 */
public class PersonBuilderPattern {
    private int age;
    private String name;
    private String socialNO;
    private String sex;

    public PersonBuilderPattern(int age,String name,String socialNO,String sex){
        this.age = age;
        this.name = name;
        this.socialNO = socialNO;
        this.sex = sex;
    }

    static class Builder{
        private int age;
        private String name;
        private String socialNO;
        private String sex;

        public Builder setAge(int age){
            this.age = age;
            return this;
        }

        public Builder setName(String name){
            this.name = name;
            return this;
        }

        public Builder setSocialNO(String socialNO){
            this.socialNO = socialNO;
            return this;
        }

        public Builder setSex(String sex){
            this.sex = sex;
            return this;
        }

        public PersonBuilderPattern builder(){
            return new PersonBuilderPattern(this.age,this.name,this.socialNO,this.sex);
        }
    }

    public static void main(String[] args) {
        PersonBuilderPattern personBuilderPattern = new PersonBuilderPattern.Builder().setAge(10).setName("yaoli").builder();
        System.out.println(personBuilderPattern.getName());
        System.out.println(personBuilderPattern.getAge());
        System.out.println(personBuilderPattern.getSocialNO());
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSocialNO() {
        return socialNO;
    }

    public void setSocialNO(String socialNO) {
        this.socialNO = socialNO;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
