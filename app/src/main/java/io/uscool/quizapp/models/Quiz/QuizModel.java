package io.uscool.quizapp.models.Quiz;

import android.content.Context;

/**
 * Created by ujjawal on 23/6/17.
 */

public class QuizModel extends AbstractQuizWizardModel {
    public QuizModel(Context context) {
        super(context);
    }

    @Override
    protected QuizPageList onNewRootPageList() {
        return new QuizPageList(
                new SingleCorrectChoiceQuiz(this, "Which of the following statement is true?")
                        .setChoices("HF is less polar than HBr", "absolutely pure water doesn't contain any ions",
                                "Chemical bond formation take place when forces of attraction overcome the force of repulsion",
                                "in covalency transference of electron takes place")
                        .setChoiceMatchPosition(1)
                        .setRequired(false),

                new SingleCorrectChoiceQuiz(this, "In the anion HCOO the two carbon-oxygen bonds are found to be of equal length. What is the reason for it?")
                        .setChoices("The C=O bond is weaker than the C-O bond",
                                "The anion HCOO has two resonating structures",
                                "The anion is obtained by removal of a protoon from the acid molecule",
                                "Electronic orbitals of carbon atom are hybridised")
                        .setChoiceMatchPosition(1)
                        .setRequired(false),

                new SingleCorrectChoiceQuiz(this, "Lattice energy of an ionic compounds depends upon?")
                        .setChoices("Change on the ion only",
                                "Size of the ion only",
                                "Packing of ions only",
                                "Charge of the ion and size of the ion")
                        .setChoiceMatchPosition(1)
                        .setRequired(false),

                new SingleCorrectChoiceQuiz(this, "The number and type of bonds between two carbon atoms in calcium carbide are")
                        .setChoices("One sigma, one pi",
                                "One sigma, two pi",
                                "Two sigma, one pi",
                                "Two sigma, two pi")
                        .setChoiceMatchPosition(1)
                        .setRequired(false),


                new SingleCorrectChoiceQuiz(this, "Question One")
                        .setChoices("A", "B")
                        .setChoiceMatchPosition(1)
                        .setRequired(true),

                new SingleCorrectChoiceQuiz(this, "Question Two")
                        .setChoices("A", "B", "C",
                                "D", "E", "F")
                        .setChoiceMatchPosition(1)
                        .setRequired(true),

                new SingleCorrectChoiceQuiz(this, "Question Three")
                        .setChoices("A", "B", "C")
                        .setChoiceMatchPosition(1)
                        .setRequired(false)
        );
    }

}
