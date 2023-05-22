public class Scoreboard {
    Score[] scoring;
    Scoreboard(Score[] scoring){this.scoring = scoring;}
 
    
    String littleBuncoWinner(){
        int maxbuncos=-1 ;      //maxbuncos is -1 because of the condition on the if clause
        String info = "LITTLE BUNCO!      \n";
        for(int i =0;i<scoring.length;i++){
            if(scoring[i].getBuncos()>maxbuncos){
                maxbuncos = scoring[i].getBuncos();
        }}
        for(int i=0;i<scoring.length;i++){
            if(scoring[i].getBuncos()==maxbuncos){
                info += scoring[i].getName() + " has " + maxbuncos + " total little buncos\n\t" + "       "; 
             }
        }
        info += "\n";
        return info;
    }

    String bigBuncoWinner(){
        int maxbuncos=-1 ;
        String info = "BIG BUNCO!       \n";
        for(int i =0;i<scoring.length;i++){
            if(scoring[i].getbigBuncos()>maxbuncos){
                maxbuncos = scoring[i].getbigBuncos();
        }}
        for(int i=0;i<scoring.length;i++){
            if(scoring[i].getbigBuncos()==maxbuncos){
                info += scoring[i].getName() + " has " + maxbuncos + " total BIG buncos\n\t" + "       "; 
             }
        }
        info += "\n";
        return info;
    }
    String totalScoreWinner(){
    
        int max=-1 ;
        String info = "SCORE WINNER!\n" + "      ";
        
        for(int i =0;i<scoring.length;i++){
            if(scoring[i].getTotalPoints()>max){
                max = scoring[i].getTotalPoints();
        }}
        for(int i=0;i<scoring.length;i++){
            if(scoring[i].getTotalPoints()==max){
                info += scoring[i].getName() + " has " + max + " POINTS\n\t" + "        "; 
             }
        }
        info += "\n";
        return info;
    }
    String roundsWinner(){
        int max=-1 ;
        String info = "ROUNDS WON!       \n";
        for(int i =0;i<scoring.length;i++){
            if(scoring[i].getroundsWon()>max){
                max = scoring[i].getroundsWon();
        }}
        for(int i=0;i<scoring.length;i++){
            if(scoring[i].getroundsWon()==max){
                info += scoring[i].getName() + " has " + max + " total rounds won\n\t" + "        "; 
             }
        }
        info += "\n";
        return info;
    }
    
    public String printStats(){
        String total = "\n";
        for( int i = 0; i < scoring.length; i++) {
            total += scoring[i] + "        ";
        }
        return total+"\n";
    }
    public String toString(){
        String total = printStats() + totalScoreWinner() + roundsWinner() + littleBuncoWinner() + bigBuncoWinner();
        //String total = totalScoreWinner() + "\n" + roundsWinner() + "\n" + littleBuncoWinner() + "\n" + bigBuncoWinner() + "\n";
        //String total = totalScoreWinner();
        return total;  
    }
}