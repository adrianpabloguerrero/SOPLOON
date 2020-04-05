class Auth {
  constructor() {
    this.authenticated = false;
    this.token = "";
  }

  login(token) {
    this.authenticated = true;
    this.token = token;
  }

  logout() {
    this.authenticated = false;
  }

  isAuthenticated() {
    return this.authenticated;
  }
}

export default new Auth();
