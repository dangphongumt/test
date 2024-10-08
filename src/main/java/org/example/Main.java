package org.example;

import org.example.entity.Customer;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Hello world!");
//        readFile();
//        writeFile();
        hackerRankDataType();
    }

    public static void hackerRankDataType() {
        Scanner sc = new Scanner(System.in);
        System.out.println("start");
        int t=sc.nextInt();

        for(int i=0;i<t;i++) {
            try
            {
                long x=sc.nextLong();
                System.out.println(x+" can be fitted in:");
                if(x>=-128 && x<=127)System.out.println("* byte");//2^8 = 256
                if (x>= -32768 && x<=32767) System.out.println("* short");//2^16 = 64000
                if (x>= -2147483648 && x<= 2147483647) System.out.println("* int");//2^32 = 4byte
                if (x>= -9223372036854775808L && x<= 9223372036854775807L) System.out.println("* long");//2^64 = 8byte
            }
            catch(Exception e)
            {
                System.out.println(sc.next()+" can't be fitted anywhere.");
            }

        }
    }

    static void readFile() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("cus3.dat")));
        System.out.println("Customer after deserialization:");
        Customer customer = (Customer) ois.readObject();
        System.out.println(customer);
        ois.close();
    }

    static void writeFile() throws IOException{
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File("cus3.dat")));
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("kai");
        customer.setAddress("ha noi");

        System.out.println("Customer before serialization:");
        System.out.println(customer);
        objectOutputStream.writeObject(customer);
        objectOutputStream.close();
    }


    static void testMap() {
        // khoi tao map
//        TreeMap<String, String> treeMap = new TreeMap<String, String>();
//        Map<String, String> map = new HashMap<String, String>();
//         // them cac phan tu vao map
//        map.put("J", "Java");
//        map.put("J", "abc");
//        map.put("C", "C++");
//        map.put("d","PHP");
//        map.put("P", "PHP");
//        map.put("Py", "Python");
//        // show hashMap
//        Iterator<String> itr = map.keySet().iterator();
//        while (itr.hasNext()) {
//            System.out.println(map.get(itr.next()));
//        }
//        List<Transaction> transactions = Arrays.asList(new Transaction("121", "3000","ABC"),//->3100
//                                                        new Transaction("123", "100","sdfsf"),
//                                                        new Transaction("121", "100","ABC"),
//                                                        new Transaction("122", "1000","2222222"),
//                                                        new Transaction("124", "5000","DEF:ghi"),//->5100
//                                                        new Transaction("125", "10000","3333333"),
//                                                        new Transaction("124", "100","DEF: Custom"));
//        mergeTransaction(transactions);
//        tess();
    }





    static  void tess(){
        HashMap hm = new HashMap();
        // Dat cac phan tu vao map
        hm.put("Zara", new Double(3434.34));
        hm.put("Mahnaz", new Double(123.22));
        hm.put("Ayan", new Double(1378.00));
        hm.put("Daisy", new Double(99.22));
        hm.put("Qadir", new Double(-19.08));

        // Lay mot set cac entry
        Set set = hm.entrySet();

        // Lay mot iterator
        Iterator i = set.iterator();
        // Hien thi cac phan tu
        while(i.hasNext()) {
            Map.Entry me = (Map.Entry)i.next();
            System.out.print(me.getKey() + ": ");
            System.out.println(me.getValue());
        }
        System.out.println();
        // Gui 1000 vao trong tai khoan cua Zara
        double balance = ((Double)hm.get("Zara")).doubleValue();
        hm.put("Zara", new Double(balance + 1000));
        System.out.println("Balance moi cua Zara la: " +
                hm.get("Zara"));
    }
      static void mergeTransaction(List<Transaction> transactions){

        transactions.stream().forEach(i -> System.out.println("---id:"+i.getId()+"---amount:"+i.getAmount()+"---des:"+i.getDes()));

          Map<String, Integer> mapSum = transactions.stream()
                  .collect(Collectors.groupingBy(Transaction::getId,
                          Collectors.summingInt(t -> Integer.parseInt(t.getAmount())))).entrySet().stream().filter(k ->k.getValue() > 1).collect(Collectors.toMap(e->e.getKey(),e->e.getValue()));


          Set<String> setmapSum = mapSum.keySet();

          for (String key: setmapSum){
              System.out.println("mapSum---key: "+key+" -----value: "+mapSum.get(key));
          }
          Map<String, String> mapSUMDES = transactions.stream()
                  .collect(Collectors.groupingBy(Transaction::getId,
                          Collectors.mapping(Transaction::getDes,
                                  Collectors.joining(","))));

          Map<String, Long> mapCount = transactions.stream()
                  .collect(Collectors.groupingBy(Transaction::getId,
                          Collectors.counting()));
          Set<String> set = mapCount.keySet();

          for (String key: set){
              System.out.println("key: "+key+" -----value: "+mapCount.get(key));
              if(mapCount.get(key) > 1){
                  transactions.stream().forEach(i -> {
                      if(i.getId().equals(key)){
                          i.setAmount(String.valueOf(mapSum.get(key)));
                            i.setDes(mapSUMDES.get(key));

                      }
                  });
              }
          }


          transactions.stream().forEach(i -> System.out.println("---id:"+i.getId()+"---amount:"+i.getAmount()+"---des:"+i.getDes()));

//          transactions.stream().filter(i->transactions.stream().collect(Collectors.groupingBy(Transaction::getId,Collectors.counting())).entrySet())
//          transactions.stream().collect(Collectors.groupingBy(Transaction::getId,Collectors.counting())).entrySet()

//          List<String> duplicates = transactions.stream()
//                  .collect(Collectors.groupingBy(Transaction::getId, Collectors.counting()))
//                  .entrySet().stream()
//                  .filter(e -> e.getValue() > 1)
//.map(Map.Entry::getKey)
//                  .collect(Collectors.toList());
//
//          System.out.println(duplicates);
    }

     static class Transaction{
        String id;
        String amount;

        String des;

         public Transaction(String id, String amount, String des) {
             this.id = id;
             this.amount = amount;
             this.des = des;
         }

         public String getDes() {
             return des;
         }

         public void setDes(String des) {
             this.des = des;
         }

         public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
    }
}
