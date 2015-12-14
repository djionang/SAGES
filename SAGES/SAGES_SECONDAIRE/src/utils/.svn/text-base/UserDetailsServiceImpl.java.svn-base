package utils;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.springframework.dao.DataAccessException;

import ages.beans.auth.UserBean;

public class UserDetailsServiceImpl implements UserDetailsService {

	private Service service;

	public UserDetailsServiceImpl() {		
		
	}

	public UserDetails loadUserByUsername(String login)
			throws UsernameNotFoundException, DataAccessException {
		
		UserBean user = service.rechercherUtilisateurParLogin(login);
		if (user == null)
			throw new UsernameNotFoundException("User not found: " + login);
		else {
			return makeAcegiUser(user);
			
		}
		
		
	}

	private org.acegisecurity.userdetails.User makeAcegiUser(UserBean user) {
		return new org.acegisecurity.userdetails.User(user.getLogin(), user
				.getPassword(), true, true, true, true,
				makeGrantedAuthorities(user));
	}

	private GrantedAuthority[] makeGrantedAuthorities(UserBean user) {
		GrantedAuthority[] result = new GrantedAuthority[user.getRoles().size()];
		int i = 0;
		for (String role : user.getRoles()) {
			result[i++] = new GrantedAuthorityImpl(role);
		}
		return result;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

}
