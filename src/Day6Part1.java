import java.util.*;

public class Day6Part1 {
    public static void main(String[] args) {

        ArrayDeque<Char> list = new ArrayDeque<>(4);

        char current;
        Char currentC;
        int unique;
        int i = 0;
        int nUnique = 0;
        while (true) {
            if (i > 3) {
                nUnique -= list.removeFirst().uniqueFlag();
            }
            current = input.charAt(i);
            unique = Char.checkUnique(list, current);
            list.addLast(new Char(current, unique));
            nUnique += unique;

            if (nUnique == 4 && i > 3) {
                System.out.println(i);
                break;
            }

            i++;
        }
    }



    public record Char (char c, int uniqueFlag){

        static int checkUnique(ArrayDeque<Char> list, char check){
            for (Char curr: list
                 ) {
                if (curr.c == check){
                    return 0;
                }
            }
            return 1;
        }
    }

    static final String input = """
            cmpmbppqmqsqzzrrswrrnqrnqqjzjvzzqvzvjvnnlclrrjhrrrnggmgwwdhhfttmmjrjzrzrhrbrgbrbsbjbcjjvpjjcvjvttfwffjtffqqqldqllhthhljhllfffbfbzbczzznmznnrrtgrgppdcppdfpfjjrggpjgjwgjwgghdggzzshzhrhttmhhdqhqsqgglhljjbggjhggfsfvfggrmggwwbwvvwssqtqzqlqvlqqvtthjhjrjssclscsszhzbztbbdhdbbqfbqqvdqdjjjspsqslsnlljnljjhttdtbdttjpjggtfftlldpdzzqhzqqcwwvggmgmppmhpmpddgjjbzbmzmllndldblbwbswsbbmzzwnnpdndzznmnjnjmnnzwwrqwrqrvqvqmmbmhmzzdqqfnqqgnqqfppmnnqzzvvcfclcrrzfzrzttchttshsphshddrgdggvwgvwwjjzbbprrgwrrcttwbwpwfffqppwnpwpqwwwbmwwjrjprrrsfrfdfrddvdzvzqzgzmgmpgpmmpspdssdlsldsdwssgzgddttqpphllvbvccjzjppffsjsbbvnbvbttgwtgtftzzlhzzjvzvsvtsttfrfrsswpwnwccqtqrqqrhqhjhccpfpgpjgpprnprrscctddqmdqqncqctqqrpqrprwpwnwnrnccmfmdfmfdfnfdnnphhbhttsszgssvhhprprnrsrrvggdllvfvrvbrbsrbrpphhqbbhllpttjmttrstszzwllbsbzzgmzgzbzcbcbjcbbjdjpjtttwggqhhpzpzszqqzhqqhrhlrrrvnrnqrqgrqggzccnnjrrcmmzvmmvtmtltvvlzlmmbnmmbbclljnjhhbjbhbjbbfcbfcfbbqwwftfhttwvwhhwfwcffwvwmmwtwftfjttwdtwdtwttqnttmdtdjtdjjlzjzhjhwwnbbmdmhhjmhmjjwmmpsmmhrrfnnqrqjrrpmpjpzpbzpztppswsmsnnlnppscsgcgsccsfcctbtcctvcvhvjhhccppbrbcrbrmbmvmllnmllgfgmgdmmslmlrmllhttgrrsttlmmnrrrlqqsjjddzzsbsdbbrjbjvbbfwwwglljplpglltlztzvtzzmbzbmbmcchlchcnhccbhhhzrrglgmgwwwwrddvmmvdvvpjpnpspmpsmpsscrrphpdhdllrsspcspsqslsspbbmcbmmdhdwhwpphfhmmfhmhjmjrrhtrtffsddwsddwccvncczlzjzdjzddszdsstgtbthhjhddwppvhvvvwrvwrrpspjpvjjsmsccszzgpzzmjzmzvmvrmmgbglgppbfppvvqffgdgtgpgdgnghhbhgbbpbdbrrjrtjtggvzzvttwbwlbwbttrbttvdttpctcrtrccddfrrmqqjrqqsvqsqzsslnlggmrggnllvdvbvmbmsmwmfmvvdwvwbbnddvfvzzjppbpgpvgppblbzznsscwssqppgbbfwwfmfzzqqgnnjnffvbfvbbfllbhlltvtnvnbnsnddcjcbjbbjpbbhhjghhqjjlttnmmhwmhwwddlppsjppgtthllsclcvvlfvvvlzvlvcccrhrrvqrvvzrvzrrmqqbtbnnwssvhvtttnsttrnnghgdggmgdmmnbnhbhdbddvppvhvlvttzmttljlflvfvpfpwffqbffjllnvlltslsttrvrdrmddtwdwmmlplhlnlffmzzrssvsgsffzjfjcffrcrppzfzbbtltjljtjftfmmmrjmjqmjqmjqmjqjsqqhzzhdhrrcwwbcltfjdgmhvmqmmsclsdgmdqzcvzznwgtdbzgvlpzvdqrvwpmndtzmdpznnplmbvvmffdpbjztvzpwffvqbwmvbmtwjrdpngrftvgznmtzwzmsrvpgpzjcdwvnqplfgncgcdtjbhcqztgqpdhwlmrjbzhcfhnzqmpzzghzpfzbgwmlqztnbdnntgdcfssgzqndhfwdtrbpzbmqjgjflmcllscjnnnrzzsjhnwptjlbhpcwwhsvqqlvjzghnwvzmwtbjgwfgmpdfcjfswvqzwdbnvlwfbmdcjvgjcdhjfbjccsjqrgdrhrjnhgpvvfjqvfwqpgmgfsbsnlrfnbtpzljmzrjmjlldgbvvwbnpqgsnzzmswtwgshdlwhsttdjlhnnlgbprwltbppttctttftrmbjccvwtljqffrcpwnwjgcwjnhmphfsnbfdnfbvzqqlwbnjjdvplrjbflcrwjtrngwzznzhsmnwzfbpjrdmjlwzvrvrblrscjlrmswjpbrtjjbsgzwjnfwwgmnbbppqfnlmfsbpwbdnjmrcvqdhhvrrvmghlbbpfsqzsnbjvrvthnrhlttsnlbgvsdvsmncdglrgpsqjqthnlrhnzpnnjgvrnmdnrtjbmlppfppnmgjhtbzpztdmclgbqjzsgfjllplpnnmjhgpcfcpcmbnwjsdmfmrqvqvjfsrnqhbrzdvwcsmmjvqpjnzbgrhwcwggvvjzbrswplgvbbqhdlqptzjvzcznspjbpvfmgbcfjbgmztmqtlzmzzzpmcdmmvhvnphpcfwcmwqwwqvmwpwhhnspmhrdmjblzhhlphwldfclsfjbzhjglvllldnmtjtwqtztfcvjdngslhnsmmlwlpzdbrrthtwfpjfvfljddplfgnhcnwwmmpgwwspnsprsvprccwvvljwbqwrzqjmwfrnnjbrfsrglnrwdfnsswqwsbpplgnfnvvvhqwmtgsdwmvnzmbjpbscdtcrmsllljwlntjzpwqvvnznmfddtgrmqbdsczhtvjdwrwvjccrqnjlnqbvqhhvnmqmrmnqllcjzcjgzwctrntjsnhrwmcqzrwzzqgqlmbqczlgtmtwlztclhwcjsgbdlfrppwrpbnpgsjfhprvzjwnpdwsjwmdcbmljclcgcnsqcpzgvrrbnnhjhblgnpcbzbcdmgjmpmpcwwdngmggmfqcjcwrzblsfmrslbblhdlwjqrrgtnvcfsccbdgnjcztrblrwbrvdzcnnshwzzvgcqwpqmjzhzlllbtzmcqpnwwqqlcjtrrzfmdpmpblmwztwqdgbmtfggjwnmffzvszgdmhfvflthzmwqgqvgmldzpbwbdvrjldhbnzsthqsczttbbthzgzmmrcmzmzmjfrhbtmsbqrbnsnzzfpvwlwpszdptljqphrdznhbwbfdfhsrqnmlqdcdgbrbsmgwwbbjsmwhzgmqtmfbndzlmlvmrrvrmqbjqbhhqtvvgbhbmsrtljwmtnhtpwjvlrgqljvgpsfrwgdfbcwlmvfdrrlgwvvzvcftwpjhcplgwvqbzftjfbmmcpvrrsvbnwqdhtlsfcjzlrmwvgvgwpbffsrrpdpssqhqrqdglnwcqznzlqqvjzpsdnpwfqtfjqhqwvlfpwrqqmntcnnbfprlwrfdwstvbpjmjdbcdffmwvqbsdnvcgrtccvcfzpwjscmrtbbjjnmlcztbldfnwbqcpqlshthrcbdrfldcmhtgwrgqhnnglbzgdgglzjbgjtdvgzgrspjtbhpmzvpplgjpjbthmjtwqqzsslnfrtmpznbqvmccqccrtdvrssmdgrptsjglvrmlcwfllptczvpgwbdfbrnpzdzmpfjwdhqlsqlzlzrwcsmhcmjhhfhfvcdrzhsmqbwcfshslsnswpslbjnrzqllwbnddhrngmjqtnvhrjpcpmggvgqbwtwcmbvhnwggdrzfgmhhvpqzbvlghsvgmhngwdsrwlzffbpzqmfzvbhbjvqlcswhctpcqnptrvlwblnvpfbzsjnsdjnhqbzddsnthhcfbcvmqgfmvztlcwjbmdtgvgwqbqgdrvbgbnbcnqwzfzqpsgbvtwlphgvqlzshndcbffzcbllgzrzrnhdvqnvtndhcdslqbbhcdftlmnltmsmgfcgvmpbsljdbthjtqlfbmczznwcvfcsnftsnpzcwfqbfhjpswzfzfswfgtzplppsglsdncblddsmftmfdmmnsjjgg""";
}
