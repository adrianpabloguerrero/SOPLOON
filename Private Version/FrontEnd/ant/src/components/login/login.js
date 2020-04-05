import React, { useEffect } from "react";
import CssBaseline from "@material-ui/core/CssBaseline";
import TextField from "@material-ui/core/TextField";
import Link from "@material-ui/core/Link";
import Box from "@material-ui/core/Box";
import Typography from "@material-ui/core/Typography";
import { makeStyles } from "@material-ui/core/styles";
import Container from "@material-ui/core/Container";
import { ColorButton } from "../utils/ColorButton";
import Soploon from "../utils/images/soploon.png";
import auth from "../utils/Auth.js";
import Axios from "axios";

const useStyles = makeStyles(theme => ({
  paper: {
    marginTop: theme.spacing(8),
    display: "flex",
    flexDirection: "column",
    alignItems: "center"
  },
  avatar: {
    margin: theme.spacing(1),
    backgroundColor: theme.palette.secondary.main
  },
  form: {
    width: "100%", // Fix IE 11 issue.
    marginTop: theme.spacing(1)
  },
  submit: {
    margin: theme.spacing(3, 0, 2)
  },
  image: {
    width: "70px"
  },
  error: {
    color: "red"
  }
}));

function Copyright() {
  return (
    <Typography variant="body2" color="textSecondary" align="center">
      {"Herramienta de estadística y gestión "}
      <Link color="inherit" href="http://si.isistan.unicen.edu.ar/soploon/#/">
        Soploon
      </Link>{" "}
      {new Date().getFullYear()}
      {"."}
    </Typography>
  );
}

export default function Login({ history }) {
  const classes = useStyles();
  const [error, setError] = React.useState(false);
  const [data, setData] = React.useState({
    userName: "",
    password: ""
  });

  const config = {
    headers: {
      "Content-Type": "application/x-www-form-urlencoded"
    }
  };
  const handleSubmit = e => {
    const params = new URLSearchParams();
    params.append("userName", data.userName);
    params.append("password", data.password);

    Axios.post(
      "http://localhost:8080/soploon/api/authentication/",
      params,
      config
    )
      .then(response => {
        Axios.defaults.headers.common["Authorization"] =
          "Bearer " + response.data;
        auth.login(response.data);
        localStorage.setItem("auth", JSON.stringify(auth));
        history.push("/");
      })
      .catch(function (error) {
        setError(true);
      });
  };

  const handleChange = e => {
    const { name, value } = e.target;
    setData({ ...data, [name]: value });
  };

  useEffect(() => {
    let user = JSON.parse(localStorage.getItem("auth"));
    if (user != null && user.authenticated) {
      history.push("/");
    }
  }, []);

  return (
    <Container component="main" maxWidth="xs">
      <CssBaseline />
      <div className={classes.paper}>
        <img src={Soploon} alt="Soploon" className={classes.image} />
        <Typography component="h1" variant="h5">
          Iniciar sesión
        </Typography>
        <form className={classes.form} noValidate>
          <TextField
            variant="outlined"
            margin="normal"
            required
            fullWidth
            id="userName"
            label="Nombre de usuario"
            name="userName"
            autoComplete="userName"
            onChange={handleChange}
            autoFocus
          />
          <TextField
            variant="outlined"
            margin="normal"
            required
            fullWidth
            name="password"
            label="Contraseña"
            type="password"
            id="password"
            onChange={handleChange}
          />
        </form>
        {error && (
          <Typography className={classes.error}>
            Usuario y/o contraseña incorrecta
          </Typography>
        )}
        <ColorButton
          fullWidth
          variant="contained"
          color="primary"
          onClick={handleSubmit}
          className={classes.submit}
        >
          Ingresar
        </ColorButton>
      </div>

      <Box mt={8}>
        <Copyright />
      </Box>
    </Container>
  );
}
