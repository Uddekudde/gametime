/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.id1212.tudd.project.api.reader.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import se.kth.id1212.tudd.project.api.reader.integration.APICaller;
import se.kth.id1212.tudd.project.api.reader.model.Match;

/**
 *
 * @author udde
 */
@Stateless
public class ReaderFacade {

    private final String THIS_WEEK = "this week";
    private final String THIS_MONTH = "this month";
    private final String LAST_YEAR = "this year";
    private final String ALL_TIME = "all time";
    private final String WEEK = "7";
    private final String MONTH = "30";
    private final String YEAR = "365";
    private final String NO_LIMIT = "";
    public final String[] timeScales = {THIS_WEEK, THIS_MONTH, LAST_YEAR, ALL_TIME};

    @EJB
    APICaller caller;

    public String gameTime(String id, String timeScale) throws Exception {
        Match[] matches = null;
        switch (timeScale) {
            case THIS_WEEK:
                matches = caller.getMatchesFromID(id, WEEK);
                break;
            case THIS_MONTH:
                matches = caller.getMatchesFromID(id, MONTH);
                break;
            case LAST_YEAR:
                matches = caller.getMatchesFromID(id, YEAR);
                break;
            case ALL_TIME:
                matches = caller.getMatchesFromID(id, NO_LIMIT);
                break;
        }
        int playTimeMonth = 0;
        for (Match match : matches) {
            playTimeMonth += match.getDuration();
        }
        return secondsToHMS(playTimeMonth);

    }

    private String secondsToHMS(int playTimeMonth) {
        int hours = (playTimeMonth / 3600);
        playTimeMonth -= (3600 * hours);

        int minutes = (playTimeMonth / 60);
        playTimeMonth -= (60 * minutes);
        return hours + " hours, " + minutes + " minutes, and " + playTimeMonth + " seconds.";
    }

}
