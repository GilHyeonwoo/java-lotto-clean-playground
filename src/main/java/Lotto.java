import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLOutput;
import java.util.*;
import java.nio.charset.StandardCharsets;



public class Lotto {

    private final Set<Integer> lottoNumbers = new HashSet<>();
    private List<Integer> sortedLotto = new ArrayList<>();
    private final List<List<Integer>> lottoNum = new ArrayList<>();

    int[] rank = new int[7];
    int[] winningPrize = {0, 0, 0, 5000, 50000, 1500000, 2000000000};

    public Lotto() {
        setLottoNumbers();
        SetToList();
    }

    public void setLottoNumbers() {
        Random random = new Random();
        while (lottoNumbers.size() < 6){
            int number = random.nextInt(45) + 1;
            lottoNumbers.add(number);
        }
    }
    public List<Integer> SetToList() {
        sortedLotto = new ArrayList<>(lottoNumbers);
        Collections.sort(sortedLotto);
        return sortedLotto;
    }
    public void printLotto(int inputPrice) {
        int price = 1000;
        int loop = inputPrice/price;
        for(int i = 0; i < loop; i++) {
            Lotto generateLotto = new Lotto();
            System.out.println(generateLotto.sortedLotto);
            lottoNum.add(generateLotto.sortedLotto);
        }
        //lottoNum 이 생성된 로또 번호들을 저장 하고 있음
    }
    public List<Integer> StringToInt(String[] StringlastNum) {
        List<Integer> WinningNum = new ArrayList<>();
        for (String value : StringlastNum) {
            int lastNumber = Integer.parseInt(value);
            WinningNum.add(lastNumber);
        }
        return WinningNum;
    }

    public void checkingWinningnumbers(List<Integer> lastWinningNum) {

        for (List<Integer> singleLotto : lottoNum) {
            int matchCount = countMatchingnumbers(singleLotto, lastWinningNum);
            rank[matchCount]++;
        }
        for (int i = 3; i<7; i++) {
            System.out.println(i + "개 일치" + "(" + winningPrize[i] + "원)- " + rank[i] +"개");
        }
    }

    public int countMatchingnumbers(List<Integer> singleLotto, List<Integer> lastWinningNum) {
        int count = 0;
        for (int value : lastWinningNum) {
            count += isMaching(value, singleLotto);
        }
        return count;
    }

    public  int isMaching(int value, List<Integer> singleLotto) {
        if (singleLotto.contains(value)){
            return 1;
        }
        return 0;
    }
    //TODO: 수익률을 계산하는 매서드 추가
    public double getProfit(int money) {
        double profit = 0;
        double prize = 0;
        for (int i = 3; i<7; i++){
            if(rank[i] > 0){
                prize += winningPrize[i] * rank[i];
            }
        }
        profit = prize / money;
        return profit;
    }


    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        Lotto lotto = new Lotto();
        Scanner scanner = new Scanner(System.in);
        System.out.println("구입금액을 입력해주세요:");

        int money = scanner.nextInt();
        lotto.printLotto(money);

        scanner.nextLine();

        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String inputWinningNum = scanner.nextLine();
        String[] WinningNum = inputWinningNum.split(",");
        List<Integer> intWinningNum = lotto.StringToInt(WinningNum);
        lotto.checkingWinningnumbers(intWinningNum);
        System.out.println("총 수익률은 " + lotto.getProfit(money) + "입니다.");
    }
}
