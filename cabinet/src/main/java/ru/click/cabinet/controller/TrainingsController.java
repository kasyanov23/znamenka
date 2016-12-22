package ru.click.cabinet.controller;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import ru.click.cabinet.repository.TrainingManager;
import ru.click.cabinet.repository.model.ClientTraining;
import ru.click.core.model.Client;
import ru.click.core.model.LkUser;

import java.util.List;
import java.util.Optional;

@Controller
public class TrainingsController {

    private final TrainingManager trainingManager;

    @Autowired
    public TrainingsController(TrainingManager trainingManager) {
        this.trainingManager = trainingManager;
    }

    @GetMapping("/training")
    public ModelAndView view(@ModelAttribute Client client) {
        val mv = new ModelAndView("trainings");
        List<ClientTraining> last30Trainings = trainingManager.getLast60Trainings(client.getId());
        Optional<Integer> countTrainings = trainingManager.getBalanceOfTraining(client.getId());
        mv.addObject("trainings", last30Trainings);
        mv.addObject("count", countTrainings.orElse(0));
        return mv;
    }

    @ModelAttribute
    public Client extractClient(Authentication auth) {
        LkUser user = (LkUser) auth.getPrincipal();
        return user.getClient();
    }

}
