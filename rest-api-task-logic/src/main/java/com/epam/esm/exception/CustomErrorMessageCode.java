package com.epam.esm.exception;

/**
 * @author Ivan Velichko
 * @date 11.10.2021 11:50
 */
public interface CustomErrorMessageCode {
    String ENTITY_NOT_FOUND = "error.message.not.found.";
    String TAG_NOT_FOUND = "error.message.not.found.tag";
    String VALIDATION_ERROR = "error.message.validation";
    String CERTIFICATE_NOT_FOUND = "error.message.not.found.giftcertificate";
    String TAG_ALREADY_EXIST = "error.message.already.exist.tag";
    String CERTIFICATE_ALREADY_EXIST = "error.message.already.exist.giftcertificate";
    String TAG_CAN_NOT_BE_REMOVED = "error.tag.cant.remove";
    String TAG_NAME_INCORRECT = "error.message.tag.name.incorrect";
    String CERTIFICATE_NAME_INCORRECT = "error.message.certificate.name.incorrect";
    String CERTIFICATE_DESCRIPTION_INCORRECT = "error.message.certificate.description.incorrect";;
    String CERTIFICATE_PRICE_INCORRECT = "error.message.certificate.price.incorrect";
    String CERTIFICATE_DURATION_INCORRECT = "error.message.certificate.duration.incorrect";
}
