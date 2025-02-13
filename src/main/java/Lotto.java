import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.nio.charset.StandardCharsets;

public class Lotto {

    private final Set<Integer> lottoNumbers = new HashSet<>();
    private List<Integer> sortedLotto = new ArrayList<>();

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

    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        Scanner scanner = new Scanner(System.in);
        System.out.println("구입금액을 입력해주세요:");

        int money = scanner.nextInt();
        int price = 1000;
        int loop = money/price;
        System.out.println(loop + "개를 구매했습니다.");
        for(int i = 0; i < loop; i++) {
            Lotto lotto = new Lotto();
            System.out.println(lotto.sortedLotto);
        }
    }
}
