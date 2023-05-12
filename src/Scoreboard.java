public class Scoreboard {
    Score[] scoring;
    Scoreboard(Score[] scoring){this.scoring = scoring;}
    
    String littleBuncoWinner(){
        int maxbuncos=-1 ;
        String info = "LITTLE BUNCO!\n";
        for(int i =0;i<scoring.length;i++){
            if(scoring[i].getBuncos()>maxbuncos){
                maxbuncos = scoring[i].getBuncos();
        }}
        for(int i=0;i<scoring.length;i++){
            if(scoring[i].getBuncos()==maxbuncos){
                info += scoring[i].getName() + " has " + maxbuncos + "total little buncos\n\t"; 
             }
        }
        info += "\n";
        return info;
    }

    String bigBuncoWinner(){
        int maxbuncos=-1 ;
        String info = "BIG BUNCO!\n";
        for(int i =0;i<scoring.length;i++){
            if(scoring[i].getBuncos()>maxbuncos){
                maxbuncos = scoring[i].getBuncos();
        }}
        for(int i=0;i<scoring.length;i++){
            if(scoring[i].getBuncos()==maxbuncos){
                info += scoring[i].getName() + " has " + maxbuncos + "total BIG buncos\n\t"; 
             }
        }
        info += "\n";
        return info;
    }
    String totalScoreWinner(){
        int maxbuncos=-1 ;
        String info = "WINNER!\n";
        for(int i =0;i<scoring.length;i++){
            if(scoring[i].getBuncos()>maxbuncos){
                maxbuncos = scoring[i].getBuncos();
        }}
        for(int i=0;i<scoring.length;i++){
            if(scoring[i].getBuncos()==maxbuncos){
                info += scoring[i].getName() + " has " + maxbuncos + "POINTS\n\t"; 
             }
        }
        info += "\n";
        return info;
    }
    String roundsWinner(){
        int maxbuncos=-1 ;
        String info = "ROUNDS WON!\n";
        for(int i =0;i<scoring.length;i++){
            if(scoring[i].getroundsWon()>maxbuncos){
                maxbuncos = scoring[i].getroundsWon();
        }}
        for(int i=0;i<scoring.length;i++){
            if(scoring[i].getroundsWon()==maxbuncos){
                info += scoring[i].getName() + " has " + maxbuncos + "total rounds\n\t"; 
             }
        }
        info += "\n";
        return info;
    }
    
    public String printStats(){
        String total = "\n";
        for( int i = 0; i < scoring.length; i++) {
            total += scoring[i] + "\n";
        }
        return total+"\n";
    }
    public String toString(){
        String total = printStats() + totalScoreWinner() + roundsWinner() + littleBuncoWinner() + bigBuncoWinner();
        return total;  
    }
}
