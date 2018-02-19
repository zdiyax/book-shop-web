package kz.epam.zd.service;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for services. Contains parameters to be passed to DAOs.
 */
abstract class AbstractService {
    List<Object> parameters = new ArrayList<>();
}
