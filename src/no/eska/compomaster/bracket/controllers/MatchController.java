package no.eska.compomaster.bracket.controllers;

import no.eska.compomaster.bracket.models.Match;
import no.eska.compomaster.bracket.views.MatchView;

/**
 * Created by Eska on 14.04.2016.
 */
public class MatchController {

    private MatchView matchView;
    private Match match;

    public MatchController() {
        matchView = new MatchView();

    }

}
