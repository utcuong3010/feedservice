

import java.io.IOException;

import com.visikard.service.SocialFeedService;
import com.visikard.service.SocialFeedServiceImpl;

/**
 *
 * @author root
 */
public class TestService {
    /*
     * To change this template, choose Tools | Templates
     * and open the template in the editor.
     */

    private static final int CREATE = 1;
    private static final int DELETE = 2;
    private static final int GET = 3;
    private static final int ADD = 5;
    private static final int DEDUCT = 6;
    private static final int CREATEWATCH = 7;
    public final Monitor readStats = new Monitor();

    public void createBalanceNode(final int mode) throws IOException {
        int runs = 1;
        for (int i = 0; i < runs; i++) {
            try {
                Runnable task = new Runnable() {
                    SocialFeedService feedService = new SocialFeedServiceImpl();
                    public int startIndex;

                    public Runnable setStart(int startIndex) throws IOException, InterruptedException {
                        this.startIndex = startIndex;
                        return this;
                    }

                   
                    public void run() {
                        long start = System.nanoTime();
                        int countter = 20;
                        for (int j = 0; j < countter; j++) {
                            long startTime1 = System.nanoTime();
                            long nodeId = startIndex * 100000 + (j + 1);


                           
                            switch (mode) {
                                case CREATE:
                                    // balanceService.createBalance(dto);
                                    break;
                                case DELETE:
                                    //   balanceService.deleteBalance(dto);
                                    break;
                                case GET:
                                    //    balanceService.getBalance(dto);
                                    break;
                                case ADD:
                                    System.out.println("add");
//                                    feedService.addMoney(dto);
                                    break;
                                case DEDUCT:
//                                    feedService.deductMoney(dto);
                                    break;
                                default:
                                    break;
                            }
                            readStats.addMicro((System.nanoTime() - startTime1) / 1000);
                        }
                        long endTime = System.nanoTime();

                        System.out.println(
                                "Total ms " + startIndex + " " + (endTime - start) / 1000000d);
                        double avarage = ((endTime - start) / 1000000d) / (double) countter;

                        System.out.println(
                                "Evarage ms " + startIndex + " " + avarage);
                        System.out.println(
                                "total reg for 1s " + startIndex + " " + (1000d / avarage));
                    }
                }.setStart(i);
                new Thread(task).start();
            } catch (IOException ex) {
                System.out.println("TEST FAIL");
            } catch (InterruptedException ex) {
                System.out.println("TEST FAIL");
            }
        }
    }
}
