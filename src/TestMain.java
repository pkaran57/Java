public class TestMain {

    public static void main(String[] args){

        try{
            throw new ArithmeticException("Math error you dumb ass.");
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            System.out.println("finally gets executed before catch statement.");
        }
    }
}
