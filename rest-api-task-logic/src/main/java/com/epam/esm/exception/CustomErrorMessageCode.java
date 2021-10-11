package com.epam.esm.exception;

/**
 * @author Ivan Velichko
 * @date 11.10.2021 11:50
 */
public interface CustomErrorMessageCode {
    String ENTITY_NOT_FOUND = "error.message.not.found.";
    String TAG_NOT_FOUND = "error.message.not.found.tag";
    String CERTIFICATE_NOT_FOUND = "error.message.not.found.giftcertificate";
    String TAG_ALREADY_EXIST = "error.message.already.exist.tag";
    String CERTIFICATE_ALREADY_EXIST = "error.message.already.exist.giftcertificate";
    String TAG_CAN_NOT_BE_REMOVED = "error.tag.cant.remove";
}
