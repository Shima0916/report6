package jp.ac.uryukyu.ie.e225733;

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Suujiate{
    public static void main(String[] args) {
        // コンピュータの答えをランダムに取得する。
        int comAnser = getComAnswer();
        
        // 正解するまで何度でも繰り返す
        while (true) {
        
            // ユーザの予想値の取得
            int userGuess = getUserGuess();
        
            // 正解/不正解のチェック
            boolean judge = isJudgeAnswer(comAnser, userGuess);
        
            // 正解の場合
            if (judge) {
            break; // 処理の終了
            }else { // 不正解の場合
            // 何もしない(次のループ)
            }
        }
    }
     
    // コンピュータの答えを返すメソッド
    public static int getComAnswer() {
        // 作成した乱数を返す

        //ランダムで選ばれる数字で各桁の数字の重複を無くしている。
        while(true){
            //コンピューターの値は(１００〜９９９)から選ばれる。
            int com_guess = new Random().nextInt(1000)+100;
            //100の位
            int com_place_100 = com_guess / 100;
            //10の位
            int com_place_10 = (com_guess - (com_place_100 * 100)) / 10;
            //1の位
            int com_place_1 = ((com_guess - (com_place_100 * 100)) - com_place_10 * 10);

            if ((com_place_1 == com_place_10) || (com_place_1 == com_place_100) || (com_place_10 == com_place_100)){
            } else {
                return com_guess;
            }
        }
    }
    
    // キーボードから入力された数値(予想値)を返すメソッド
    public static int getUserGuess() {
        int guess;
        // キーボードの準備
        Scanner stdin = new Scanner(System.in);

        //break起こるまで、無限ループ
        while (true) {
            try {
                System.out.println("予想値(１００～９９９）の入力");
                // キーボードから予想値を読み込む
                guess = stdin.nextInt();
                int len = String.valueOf(guess).length();

                //２桁以外の数字で出力される
                if(len!=3){
                    System.out.println("３桁で入力してください");
                //3桁の数字だとそのままループ抜ける
                }else{
                    break;// 整数として読み込めたので無限ループの終了
                }
            //数字以外が入力されたらエラーとして例外処理。
            } catch (Exception e) {
                System.out.println("予想値は整数を入力してください");
                stdin.next();
            }
        }
        return guess; // 読み込んだ整数を返す
    }

    
    // 答えと予想値が一致するかどうかを判定するメソッド
    public static boolean isJudgeAnswer(int com, int user) {
        int Eat = isJudgeEat(com, user);
        int Bite = isJudgeBite(com, user);

        // Eat=3,Bite=3の時、正解とする
        if ((Eat==3)&(Bite==3)) {
            System.out.println("正解です！！");
            // 不正解なのでtrueを返す
            return true;

        // 不正解の場合は、現在のEat数と、Bite数を返す。
        } else {
            System.out.println(Eat+"イート,"+(Bite-Eat)+"バイト"+"です。");
            // 不正解なのでfalseを返す
            return false;
        }
    }

    //Eatを判断するメソッド
    public static int isJudgeEat(int com, int user) {
        ArrayList<Integer> Eat_com = List_com(com);
        ArrayList<Integer> Eat_user = List_com(user);
        int Count_Eat = 0; 

        //数字の位置が当たっているかを判断
        for(int i = 0; i< Eat_com.size();i++){
            int com_x = Eat_com.get(i);
            int user_x = Eat_user.get(i);
            if (com_x==user_x){
                Count_Eat++;
            }
        }
        //Eatの数を返す
        return Count_Eat;
    }

    //Biteを判断するメソッド
    public static int isJudgeBite(int com, int user){
        ArrayList<Integer> Bite_com = List_com(com);
        ArrayList<Integer> Bite_user = List_com(user);
        int Count_bite = 0;

        //数字が当たってるかを判断する
        for (int i:Bite_com){
            for (int j:Bite_user){
                if(i==j){
                    Count_bite++;
                }
            }
        }
        //Biteの数を返す
        return Count_bite;
    }

    //computerの数字の各桁をそれぞれリストに収納するメソッド
    public static ArrayList<Integer> List_com(int com){
        ArrayList<Integer> x = new ArrayList<>();
        String com_String = Integer.toString(com);

        //xに各桁の数字をリストとして保存
        for (int i = 0; i < com_String.length(); i++) {
            x.add(Character.getNumericValue(com_String.charAt(i)));
        }        


        return x;
    }

    //userの数字の各桁をそれぞれリストに収納するメソッド
    public static ArrayList<Integer> List_user(int user){
        ArrayList<Integer> y = new ArrayList<>();    
        String user_String = Integer.toString(user);

        //yに各桁の数字をリストとして収納
        for (int i = 0; i < user_String.length(); i++) {
        y.add(Character.getNumericValue(user_String.charAt(i)));
        }        

        return y;
    }
}

