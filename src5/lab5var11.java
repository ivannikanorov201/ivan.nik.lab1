import java.io.*;
import java.util.Scanner;
/*В исходном файле содержится произвольное количество чисел типа int.
 В результирующий файл переписать все числа, и в конце добавить сумму и среднее
значение.*/

public class lab5var11 {
    public static void main(String[] args) throws IOException {
        DataOutputStream dout = null;
        DataOutputStream dout2 = null;
        DataInputStream din = null;
        DataInputStream din2 = null;
        File f2;
        try{
            File f1 = new File("src.txt");
            if (f1.exists()) f1.delete();
            f1.createNewFile();
            Scanner sc = new Scanner(System.in);
            System.out.println("count: ");
            int size = sc.nextInt();
            dout = new DataOutputStream(new FileOutputStream(f1.getAbsolutePath()));
            for (int i = 0; i < size; i++) {
                int n = sc.nextInt();
                dout.writeInt(n);
            }
            din = new DataInputStream(new FileInputStream(f1));
            f2 = new File("result.txt");
            if(f2.exists()) f2.delete();
            f2.createNewFile();
            int sum = 0;
            dout2 = new DataOutputStream(new FileOutputStream(f2));
            for (int i = 0; i < size; i++) {
                int number = din.readInt();
                sum = sum + number;
                dout2.writeInt(number);
            }
            dout2.writeInt(sum);
            dout2.writeDouble((double) sum / size);
            din2 = new DataInputStream(new FileInputStream(f2));
            for (int i = 0; i < (size+1); i++) {
                System.out.print(din2.readInt() + " ");
            }
            System.out.println("Avg: " + din2.readDouble());
        }
        catch (EOFException eof){
            System.out.println("Достигнут конец списка");
        }
        catch(IOException ioException){
            
        }
        finally {
            dout.flush();
            dout.close();
            din.close();
            dout2.flush();
            dout2.close();
            din2.close();
        }
    }
}
