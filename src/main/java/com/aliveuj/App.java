package com.aliveuj;

import com.robrua.orianna.api.core.GameAPI;
import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.common.QueueType;
import com.robrua.orianna.type.core.common.Region;
import com.robrua.orianna.type.core.game.Game;
import com.robrua.orianna.type.core.league.League;
import com.robrua.orianna.type.core.staticdata.Champion;
import com.robrua.orianna.type.core.summoner.Summoner;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    static void summonerInfo(String Name){
        Summoner summoner = RiotAPI.getSummonerByName(Name);
        System.out.println("level: " + summoner.getLevel()) ;
        System.out.println("id: " + summoner.getID());
        System.out.println("tear: " + summoner.getLeagueEntries().get(0).getTier());

        List<Game> games = GameAPI.getRecentGames(summoner.getID());
        int winCnt = 0;
        for ( Game game : games) {
            System.out.println(game.getCreateDate() +"/ "
                    +game.getStats().getTimePlayed()/60+"분"+game.getStats().getTimePlayed()%60+"초"+"/ "
                    +game.getStats().getWin()+"/ "
                    +game.getStats().getKills()+" : "+game.getStats().getDeaths()+" : "+game.getStats().getAssists()+"/ "
                    +game.getChampion());
            //game.getFellowPlayers()
            if(game.getStats().getWin()){
                winCnt ++;
            }
        }
        System.out.println("최근 10게임 "+winCnt+"승"+(10-winCnt)+"패");
    }
    public static void main( String[] args )
    {
        RiotAPI.setRegion(Region.KR);
        RiotAPI.setAPIKey("RGAPI-766207f5-d8e8-4966-9982-4610f9b9317d");

        summonerInfo("Exploiter");
        summonerInfo("R개발자");

/*        List<Champion> champions = RiotAPI.getChampions();
        System.out.println("He enjoys playing LoL on all different champions, like " + champions.get((int)(champions.size()-1))+ ".");

        League challenger = RiotAPI.getChallenger(QueueType.RANKED_SOLO_5x5);
        Summoner bestKR = challenger.getEntries().get(0).getSummoner();
        System.out.println("랭크게임 1위: " + bestKR + ".");*/
    }
}
