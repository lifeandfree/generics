package ru.innopolis.dao.list;

import ru.innopolis.dao.OrganizationDAO;
import ru.innopolis.model.Organization;
import ru.innopolis.model.User;

import java.util.ArrayList;

/**
 * OrganizationDAOImpl.
 *
 * @author Ilya_Sukhachev
 */
public class OrganizationDAOImpl extends AbstractDao<Organization, Long>
        implements OrganizationDAO<Organization, Long> {

    public OrganizationDAOImpl() {
        super(Organization.class, new ArrayList<>());
    }

    @Override
    public Organization getByName(String organizationName) {
        for (Organization el:elements) {
            if (el.getOrganizationName().equals(organizationName)) {
                return el;
            }
        }
        return null;
    }

    @Override
    public Organization getByUser(User user) {
        for (Organization organization:elements) {
            if (organization.getUsers().contains(user)) {
                return organization;
            }
        }
        return null;
    }
}
