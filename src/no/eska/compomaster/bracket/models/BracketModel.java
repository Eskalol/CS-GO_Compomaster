package no.eska.compomaster.bracket.models;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Eska on 10.04.2016.
 */
public class BracketModel {
    private HashMap<String, Match> matches;
    private ArrayList<Team> teams;

    public BracketModel(ArrayList<Team> teams) {
        this.teams  = teams;
        matches     = new HashMap<>();
    }


    /**
     * returns the hashmap with matches
     * @return matches hashmap
     */
    public HashMap<String, Match> getMatches() {
        return this.matches;
    }

    /**
     * initializing match model, puts label etc... dont tuch please
     * TODO: too hacky, not sleek, not wow :(
     * @param bracketSize bracketsize
     * @param looserBracekt looserbracket bool
     */
    public void initMatchModel(int bracketSize, boolean looserBracekt) {
        int bsize = bracketSize;
        int wbc = 0;
        int lbc = 0;
        int e = 0;
        int l = 1;
        int l1= 0;
        for(int i = 0; i <= Math.pow(2, bsize); i++) {
            //l += bsize == bracketSize ? i%2 : 1;
            matches.put("wb"+(++wbc), new Match("wb"+wbc, "wb"+(int)(wbc+Math.pow(2, bsize)-i+e), "lb"+l, looserBracekt));
            //System.out.println("adding: " + "wb"+wbc + " Winner: " + "wb"+(int)(wbc+Math.pow(2, bsize)-i+e) + " looser: " + "lb"+l);
            l += bsize == bracketSize ? i%2 : 1;
            e += i%2;
            if( bsize < bracketSize && looserBracekt ) {
                matches.put("lb"+(++lbc), new Match("lb"+lbc, "lb"+(lbc+(int)Math.pow(2, bsize)), "", looserBracekt));
                //System.out.println("adding: " + "lb"+lbc + " winner: lb" + (lbc+(int)Math.pow(2, bsize)));
                matches.put("lb"+(lbc+(int)Math.pow(2, bsize)), new Match("lb"+(lbc+(int)Math.pow(2, bsize)), "lb"+(lbc+(int)Math.pow(2, bsize)+(int)Math.pow(2, bsize)-i+l1), "", looserBracekt));
                //System.out.println("adding: " + "lb"+(lbc+(int)Math.pow(2, bsize)) + " winner: lb" + (lbc+(int)Math.pow(2, bsize)+(int)Math.pow(2, bsize)-i+l1));
                l1 += i%2;
            }
            if( (i + 1) == (int)Math.pow(2, bsize) ) {
                e = 0;
                l1 = 0;
                i = -1;
                //l1 += bsize == bracketSize ? 0 : (int)Math.pow(2, bsize);
                lbc += bsize == bracketSize ? 0 : (int)Math.pow(2, bsize);
                bsize--;
            }
        }
        if(!looserBracekt) {
            matches.get("wb"+(wbc-2)).setWinnerLabel("gf");
            matches.get("wb"+(wbc-3)).setWinnerLabel("gf");
            matches.put("gf", matches.get("wb"+(wbc-1)));
            matches.remove("wb"+(wbc-1));
            matches.remove("wb"+wbc);
            matches.get("gf").setMatchLabel("gf");
            matches.get("gf").setWinnerLabel("");
            matches.get("gf").setLooserLabel("");
            return;
        }
        matches.get("wb"+wbc).setWinnerLabel("gf");
        matches.get("wb"+lbc).setWinnerLabel("gf");
        matches.get("wb"+wbc).setLooserLabel("");
        matches.get("wb"+lbc).setLooserLabel("");
        matches.put("gf", new Match("gf", "", "", looserBracekt));
    }

    /**
     * randomize teams and insert into model
     *
     */
    public void initTeamsInMatchModel() {
        Collections.shuffle(teams, new Random(System.nanoTime()));
        int i = 1;
        for(Team t : teams) {
            if(i % 2 == 0)
                matches.get("wb"+((++i-(i%2))/2)).insertTeam2(t);
            else
                matches.get("wb"+((++i-(i%2))/2)).insertTeam1(t);
        }
        if(i % 2 == 0) {
            matches.get("wb"+((i-(i%2))/2)).setTeam1Wins(-1);
            matches.get(matches.get("wb"+((i-(i%2))/2)).getWinnerLabel()).insertTeam1(matches.get("wb"+((i-(i%2))/2)).getTeam1());
        }
    }
}
