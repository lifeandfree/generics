package ru.innopolis.dao.map;

import ru.innopolis.dao.OrganizationDAO;
import ru.innopolis.model.Organization;
import ru.innopolis.model.User;

import java.util.HashMap;

/**
 * OrganizationDAOImpl.
 *
 * @author Ilya_Sukhachev
 */
public class OrganizationDAOImpl extends AbstractDao<Organization, Long>
        implements OrganizationDAO<Organization, Long> {

    public OrganizationDAOImpl() {
        super(Organization.class, new HashMap<>());
    }

    @Override
    public Organization getByName(String organizationName) {
        for (Organization el:elements.values()) {
            if (el.getOrganizationName().equals(organizationName)) {
                return el;
            }
        }
        return null;
    }

    @Override
    public Organization getByUser(User user) {
        for (Organization organization:elements.values()) {
            if (organization.getUsers().contains(user)) {
                return organization;
            }
        }
        return null;
    }
}
