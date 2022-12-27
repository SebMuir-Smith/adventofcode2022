import java.util.ArrayDeque;
import java.util.Queue;

public class Day6Part2 {

    static int[] container = new int[26];

    static Queue<Character> queue = new ArrayDeque(14);
    static int offset = 'a';
    public static void main(String[] args) {

        for (int i = 0; i < 26; i++){
            container[i] = 0;
        }

        char current;
        int unique;
        int i = 0;
        int nUnique = 0;
        while (true) {
            if (i > 13) {
                current = queue.poll();
                if (--container[current - offset] == 0){
                    nUnique--;
                }
            }
            current = input.charAt(i);
            queue.add(current);
            if(++container[current - offset] == 1){
                nUnique++;
            };

            if (nUnique == 14 && i > 13) {
                System.out.println(++i);
                break;
            }

            i++;
        }
    }



    static final String input = """
            cmpmbppqmqsqzzrrswrrnqrnqqjzjvzzqvzvjvnnlclrrjhrrrnggmgwwdhhfttmmjrjzrzrhrbrgbrbsbjbcjjvpjjcvjvttfwffjtffqqqldqllhthhljhllfffbfbzbczzznmznnrrtgrgppdcppdfpfjjrggpjgjwgjwgghdggzzshzhrhttmhhdqhqsqgglhljjbggjhggfsfvfggrmggwwbwvvwssqtqzqlqvlqqvtthjhjrjssclscsszhzbztbbdhdbbqfbqqvdqdjjjspsqslsnlljnljjhttdtbdttjpjggtfftlldpdzzqhzqqcwwvggmgmppmhpmpddgjjbzbmzmllndldblbwbswsbbmzzwnnpdndzznmnjnjmnnzwwrqwrqrvqvqmmbmhmzzdqqfnqqgnqqfppmnnqzzvvcfclcrrzfzrzttchttshsphshddrgdggvwgvwwjjzbbprrgwrrcttwbwpwfffqppwnpwpqwwwbmwwjrjprrrsfrfdfrddvdzvzqzgzmgmpgpmmpspdssdlsldsdwssgzgddttqpphllvbvccjzjppffsjsbbvnbvbttgwtgtftzzlhzzjvzvsvtsttfrfrsswpwnwccqtqrqqrhqhjhccpfpgpjgpprnprrscctddqmdqqncqctqqrpqrprwpwnwnrnccmfmdfmfdfnfdnnphhbhttsszgssvhhprprnrsrrvggdllvfvrvbrbsrbrpphhqbbhllpttjmttrstszzwllbsbzzgmzgzbzcbcbjcbbjdjpjtttwggqhhpzpzszqqzhqqhrhlrrrvnrnqrqgrqggzccnnjrrcmmzvmmvtmtltvvlzlmmbnmmbbclljnjhhbjbhbjbbfcbfcfbbqwwftfhttwvwhhwfwcffwvwmmwtwftfjttwdtwdtwttqnttmdtdjtdjjlzjzhjhwwnbbmdmhhjmhmjjwmmpsmmhrrfnnqrqjrrpmpjpzpbzpztppswsmsnnlnppscsgcgsccsfcctbtcctvcvhvjhhccppbrbcrbrmbmvmllnmllgfgmgdmmslmlrmllhttgrrsttlmmnrrrlqqsjjddzzsbsdbbrjbjvbbfwwwglljplpglltlztzvtzzmbzbmbmcchlchcnhccbhhhzrrglgmgwwwwrddvmmvdvvpjpnpspmpsmpsscrrphpdhdllrsspcspsqslsspbbmcbmmdhdwhwpphfhmmfhmhjmjrrhtrtffsddwsddwccvncczlzjzdjzddszdsstgtbthhjhddwppvhvvvwrvwrrpspjpvjjsmsccszzgpzzmjzmzvmvrmmgbglgppbfppvvqffgdgtgpgdgnghhbhgbbpbdbrrjrtjtggvzzvttwbwlbwbttrbttvdttpctcrtrccddfrrmqqjrqqsvqsqzsslnlggmrggnllvdvbvmbmsmwmfmvvdwvwbbnddvfvzzjppbpgpvgppblbzznsscwssqppgbbfwwfmfzzqqgnnjnffvbfvbbfllbhlltvtnvnbnsnddcjcbjbbjpbbhhjghhqjjlttnmmhwmhwwddlppsjppgtthllsclcvvlfvvvlzvlvcccrhrrvqrvvzrvzrrmqqbtbnnwssvhvtttnsttrnnghgdggmgdmmnbnhbhdbddvppvhvlvttzmttljlflvfvpfpwffqbffjllnvlltslsttrvrdrmddtwdwmmlplhlnlffmzzrssvsgsffzjfjcffrcrppzfzbbtltjljtjftfmmmrjmjqmjqmjqmjqjsqqhzzhdhrrcwwbcltfjdgmhvmqmmsclsdgmdqzcvzznwgtdbzgvlpzvdqrvwpmndtzmdpznnplmbvvmffdpbjztvzpwffvqbwmvbmtwjrdpngrftvgznmtzwzmsrvpgpzjcdwvnqplfgncgcdtjbhcqztgqpdhwlmrjbzhcfhnzqmpzzghzpfzbgwmlqztnbdnntgdcfssgzqndhfwdtrbpzbmqjgjflmcllscjnnnrzzsjhnwptjlbhpcwwhsvqqlvjzghnwvzmwtbjgwfgmpdfcjfswvqzwdbnvlwfbmdcjvgjcdhjfbjccsjqrgdrhrjnhgpvvfjqvfwqpgmgfsbsnlrfnbtpzljmzrjmjlldgbvvwbnpqgsnzzmswtwgshdlwhsttdjlhnnlgbprwltbppttctttftrmbjccvwtljqffrcpwnwjgcwjnhmphfsnbfdnfbvzqqlwbnjjdvplrjbflcrwjtrngwzznzhsmnwzfbpjrdmjlwzvrvrblrscjlrmswjpbrtjjbsgzwjnfwwgmnbbppqfnlmfsbpwbdnjmrcvqdhhvrrvmghlbbpfsqzsnbjvrvthnrhlttsnlbgvsdvsmncdglrgpsqjqthnlrhnzpnnjgvrnmdnrtjbmlppfppnmgjhtbzpztdmclgbqjzsgfjllplpnnmjhgpcfcpcmbnwjsdmfmrqvqvjfsrnqhbrzdvwcsmmjvqpjnzbgrhwcwggvvjzbrswplgvbbqhdlqptzjvzcznspjbpvfmgbcfjbgmztmqtlzmzzzpmcdmmvhvnphpcfwcmwqwwqvmwpwhhnspmhrdmjblzhhlphwldfclsfjbzhjglvllldnmtjtwqtztfcvjdngslhnsmmlwlpzdbrrthtwfpjfvfljddplfgnhcnwwmmpgwwspnsprsvprccwvvljwbqwrzqjmwfrnnjbrfsrglnrwdfnsswqwsbpplgnfnvvvhqwmtgsdwmvnzmbjpbscdtcrmsllljwlntjzpwqvvnznmfddtgrmqbdsczhtvjdwrwvjccrqnjlnqbvqhhvnmqmrmnqllcjzcjgzwctrntjsnhrwmcqzrwzzqgqlmbqczlgtmtwlztclhwcjsgbdlfrppwrpbnpgsjfhprvzjwnpdwsjwmdcbmljclcgcnsqcpzgvrrbnnhjhblgnpcbzbcdmgjmpmpcwwdngmggmfqcjcwrzblsfmrslbblhdlwjqrrgtnvcfsccbdgnjcztrblrwbrvdzcnnshwzzvgcqwpqmjzhzlllbtzmcqpnwwqqlcjtrrzfmdpmpblmwztwqdgbmtfggjwnmffzvszgdmhfvflthzmwqgqvgmldzpbwbdvrjldhbnzsthqsczttbbthzgzmmrcmzmzmjfrhbtmsbqrbnsnzzfpvwlwpszdptljqphrdznhbwbfdfhsrqnmlqdcdgbrbsmgwwbbjsmwhzgmqtmfbndzlmlvmrrvrmqbjqbhhqtvvgbhbmsrtljwmtnhtpwjvlrgqljvgpsfrwgdfbcwlmvfdrrlgwvvzvcftwpjhcplgwvqbzftjfbmmcpvrrsvbnwqdhtlsfcjzlrmwvgvgwpbffsrrpdpssqhqrqdglnwcqznzlqqvjzpsdnpwfqtfjqhqwvlfpwrqqmntcnnbfprlwrfdwstvbpjmjdbcdffmwvqbsdnvcgrtccvcfzpwjscmrtbbjjnmlcztbldfnwbqcpqlshthrcbdrfldcmhtgwrgqhnnglbzgdgglzjbgjtdvgzgrspjtbhpmzvpplgjpjbthmjtwqqzsslnfrtmpznbqvmccqccrtdvrssmdgrptsjglvrmlcwfllptczvpgwbdfbrnpzdzmpfjwdhqlsqlzlzrwcsmhcmjhhfhfvcdrzhsmqbwcfshslsnswpslbjnrzqllwbnddhrngmjqtnvhrjpcpmggvgqbwtwcmbvhnwggdrzfgmhhvpqzbvlghsvgmhngwdsrwlzffbpzqmfzvbhbjvqlcswhctpcqnptrvlwblnvpfbzsjnsdjnhqbzddsnthhcfbcvmqgfmvztlcwjbmdtgvgwqbqgdrvbgbnbcnqwzfzqpsgbvtwlphgvqlzshndcbffzcbllgzrzrnhdvqnvtndhcdslqbbhcdftlmnltmsmgfcgvmpbsljdbthjtqlfbmczznwcvfcsnftsnpzcwfqbfhjpswzfzfswfgtzplppsglsdncblddsmftmfdmmnsjjgg""";
}
