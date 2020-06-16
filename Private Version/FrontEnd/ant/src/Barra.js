import React from "react";
import clsx from "clsx";
import { makeStyles, useTheme } from "@material-ui/core/styles";
import Drawer from "@material-ui/core/Drawer";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import List from "@material-ui/core/List";
import CssBaseline from "@material-ui/core/CssBaseline";
import Typography from "@material-ui/core/Typography";
import Divider from "@material-ui/core/Divider";
import IconButton from "@material-ui/core/IconButton";
import MenuIcon from "@material-ui/icons/Menu";
import ChevronLeftIcon from "@material-ui/icons/ChevronLeft";
import ChevronRightIcon from "@material-ui/icons/ChevronRight";
import TrendingUpIcon from "@material-ui/icons/TrendingUp";
import ErrorIcon from "@material-ui/icons/Error";
import FolderIcon from "@material-ui/icons/Folder";
import ListItem from "@material-ui/core/ListItem";
import ListItemIcon from "@material-ui/core/ListItemIcon";
import ListItemText from "@material-ui/core/ListItemText";
import PersonIcon from "@material-ui/icons/Person";
import TocIcon from "@material-ui/icons/Toc";
import LibraryBooksIcon from "@material-ui/icons/LibraryBooks";
import Soploon from "./components/utils/images/soploon.png";
import TableUsers from "./components/users/Users.js";
import "antd/dist/antd.css";
import { Result } from "antd";
import Rules from "./components/rules/Rules.js";
import Predicates from "./components/predicates/Predicates.js";
import Errors from "./components/errors/Errors.js";
import Explorer from "./components/explorer/explorer.js";
import Dashboard from "./components/dashboard/Dashboard.js";
import ExitToAppIcon from "@material-ui/icons/ExitToApp";
import { Switch, Route, Link, BrowserRouter } from "react-router-dom";
import auth from "./components/utils/Auth";
import Axios from "axios";

const drawerWidth = 240;

const useStyles = makeStyles(theme => ({
  root: {
    display: "flex"
  },

  image: {
    width: "30px",
    marginLeft: "10px"
  },

  appBar: {
    zIndex: theme.zIndex.drawer + 1,
    transition: theme.transitions.create(["width", "margin"], {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.leavingScreen
    }),
    backgroundColor: "#222"
  },
  appBarShift: {
    marginLeft: drawerWidth,
    width: `calc(100% - ${drawerWidth}px)`,
    transition: theme.transitions.create(["width", "margin"], {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.enteringScreen
    }),
    backgroundColor: "#222"
  },
  menuButton: {
    marginRight: 36,
    color: "#9d9d9d"
  },
  hide: {
    display: "none"
  },
  drawer: {
    width: drawerWidth,
    flexShrink: 0,
    whiteSpace: "nowrap",
    backgroundColor: "#222"
  },
  drawerOpen: {
    width: drawerWidth,
    transition: theme.transitions.create("width", {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.enteringScreen
    })
  },
  drawerClose: {
    transition: theme.transitions.create("width", {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.leavingScreen
    }),
    overflowX: "hidden",
    width: theme.spacing(7) + 1,
    [theme.breakpoints.up("sm")]: {
      width: theme.spacing(9) + 1
    }
  },
  toolbar: {
    display: "flex",
    alignItems: "center",
    justifyContent: "flex-end",
    padding: theme.spacing(0, 1),
    ...theme.mixins.toolbar
  },
  content: {
    flexGrow: 1,
    padding: theme.spacing(3),
    width: `calc(100% - ${drawerWidth}px)`
  }
}));

export default function MiniDrawer({ history }) {
  const classes = useStyles();
  const theme = useTheme();
  const [open, setOpen] = React.useState(false);

  const handleDrawerOpen = () => {
    setOpen(true);
  };

  const handleLogOut = () => {
    auth.logout();
    localStorage.setItem("auth", JSON.stringify(auth));
    Axios.defaults.headers.common["Authorization"] = "";
    history.push("/login");
  };

  const handleDrawerClose = () => {
    setOpen(false);
  };

  return (
    <div className={classes.root}>
      <CssBaseline />
      <AppBar
        position="fixed"
        className={clsx(classes.appBar, {
          [classes.appBarShift]: open
        })}
      >
        <Toolbar>
          <IconButton
            color="inherit"
            aria-label="open drawer"
            onClick={handleDrawerOpen}
            edge="start"
            className={clsx(classes.menuButton, {
              [classes.hide]: open
            })}
          >
            <MenuIcon />
          </IconButton>
          <Typography variant="h6" style={{ color: "#9d9d9d" }} noWrap>
            Soploon
          </Typography>
          <img src={Soploon} alt="Soploon" className={classes.image} />
        </Toolbar>
      </AppBar>
      <BrowserRouter basename={'/soploon/admin'}>
        <Drawer
          variant="permanent"
          className={clsx(classes.drawer, {
            [classes.drawerOpen]: open,
            [classes.drawerClose]: !open
          })}
          classes={{
            paper: clsx({
              [classes.drawerOpen]: open,
              [classes.drawerClose]: !open
            })
          }}
        >
          <div className={classes.toolbar}>
            <IconButton onClick={handleDrawerClose}>
              {theme.direction === "rtl" ? (
                <ChevronRightIcon />
              ) : (
                <ChevronLeftIcon />
              )}
            </IconButton>
          </div>
          <Divider />
          <List>
            <ListItem button component={Link} to={"/"}>
              <ListItemIcon>
                <TrendingUpIcon />
              </ListItemIcon>
              <ListItemText primary="Dashboard" />
            </ListItem>
            <Divider />
            <ListItem button component={Link} to={"/users"}>
              <ListItemIcon>
                <PersonIcon />
              </ListItemIcon>
              <ListItemText primary="Usuarios" />
            </ListItem>
            <ListItem button component={Link} to={"/rules"}>
              <ListItemIcon>
                <TocIcon />
              </ListItemIcon>
              <ListItemText primary="Reglas" />
            </ListItem>
            <ListItem button component={Link} to={"/predicates"}>
              <ListItemIcon>
                <LibraryBooksIcon />
              </ListItemIcon>
              <ListItemText primary="Predicados" />
            </ListItem>
            <Divider />
            <ListItem button component={Link} to={"/explorer"}>
              <ListItemIcon>
                <FolderIcon />
              </ListItemIcon>
              <ListItemText primary="Explorer" />
            </ListItem>
            <ListItem button component={Link} to={"/errors"}>
              <ListItemIcon>
                <ErrorIcon />
              </ListItemIcon>
              <ListItemText primary="Errores" />
            </ListItem>
            <Divider />
            <ListItem button onClick={handleLogOut}>
              <ListItemIcon>
                <ExitToAppIcon />
              </ListItemIcon>
              <ListItemText primary="Log out" />
            </ListItem>
          </List>
        </Drawer>
        <main className={classes.content}>
          <div className={classes.toolbar} />
          <Switch>
            <Route exact path="/users">
              <TableUsers />
            </Route>
            <Route exact path="/rules">
              <Rules />
            </Route>
            <Route exact path="/predicates">
              <Predicates />
            </Route>
            <Route exact path="/explorer">
              <Explorer />
            </Route>
            <Route exact path="/errors">
              <Errors />
            </Route>
            <Route exact path="/">
              <Dashboard />
            </Route>
            <Route>
              <Result
                status="404"
                title="404"
                subTitle="La página que intenta visitar no existe"
              />
            </Route>
          </Switch>
        </main>
      </BrowserRouter>
    </div>
  );
}
