/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author toshi
 */
public class IdPatternValidator implements ConstraintValidator<IdPattern, Integer> {

    private int from;
    private int to;

    @Override
    public void initialize(IdPattern pattern) {
        this.from = pattern.from();
        this.to = pattern.to();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        if (value < from || value < to) {
            return false;
        }
        return true;
    }

}
