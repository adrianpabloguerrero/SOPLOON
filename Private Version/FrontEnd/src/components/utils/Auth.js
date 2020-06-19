class Auth {
	
  constructor() {
    this.authenticated = false;
    this.token = "";
	this.credentials = null;
  }

  login(credentials, token) {
    this.authenticated = true;
    this.token = token;
	this.credentials = credentials;
  }

  logout() {
    this.authenticated = false;
  }

  isAuthenticated() {
    return this.authenticated;
  }
  
}

export default new Auth();
