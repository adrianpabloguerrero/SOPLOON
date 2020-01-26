import React from 'react';
import clsx from 'clsx';
import { makeStyles, useTheme } from '@material-ui/core/styles';
import Drawer from '@material-ui/core/Drawer';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import List from '@material-ui/core/List';
import CssBaseline from '@material-ui/core/CssBaseline';
import Typography from '@material-ui/core/Typography';
import Divider from '@material-ui/core/Divider';
import IconButton from '@material-ui/core/IconButton';
import MenuIcon from '@material-ui/icons/Menu';
import ChevronLeftIcon from '@material-ui/icons/ChevronLeft';
import ChevronRightIcon from '@material-ui/icons/ChevronRight';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import PersonIcon from '@material-ui/icons/Person';
import FolderIcon from '@material-ui/icons/Folder';
import TocIcon from '@material-ui/icons/Toc';
import LibraryBooksIcon from '@material-ui/icons/LibraryBooks';
import PlaylistAddIcon from '@material-ui/icons/PlaylistAdd';
import LibraryAddIcon from '@material-ui/icons/LibraryAdd';
import Soploon from './images/soploon.png';
import TableUsers from './components/Users.js';
import ReactDOM from 'react-dom';
import 'antd/dist/antd.css';
import { Result, Button } from 'antd';
import TableProjects from './components/TableProjects.js';
import Rules from './components/Rules.js';
import Predicates from './components/Predicates.js';
import AddPredicate from './components/addPredicate.js';
import { Switch, Route, Link, BrowserRouter } from "react-router-dom";

const drawerWidth = 240;

const clasePersonalizada = {
  backgroundColor:"red",
};

const useStyles = makeStyles(theme => ({
  root: {
    display: 'flex',

  },

  image: {
    width:"30px",
    marginLeft:"10px"
  },

  appBar: {
    zIndex: theme.zIndex.drawer + 1,
    transition: theme.transitions.create(['width', 'margin'], {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.leavingScreen,
    }),
    backgroundColor:"#222",
  },
  appBarShift: {
    marginLeft: drawerWidth,
    width: `calc(100% - ${drawerWidth}px)`,
    transition: theme.transitions.create(['width', 'margin'], {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.enteringScreen,
    }),
    backgroundColor:"#222",
  },
  menuButton: {
    marginRight: 36,
    color:"#9d9d9d"
  },
  hide: {
    display: 'none',
  },
  drawer: {
    width: drawerWidth,
    flexShrink: 0,
    whiteSpace: 'nowrap',
    backgroundColor:"#222",

  },
  drawerOpen: {
    width: drawerWidth,
    transition: theme.transitions.create('width', {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.enteringScreen,
    }),

  },
  drawerClose: {
    transition: theme.transitions.create('width', {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.leavingScreen,
    }),
    overflowX: 'hidden',
    width: theme.spacing(7) + 1,
    [theme.breakpoints.up('sm')]: {
      width: theme.spacing(9) + 1,
    },
  },
  toolbar: {

    display: 'flex',
    alignItems: 'center',
    justifyContent: 'flex-end',
    padding: theme.spacing(0, 1),
    ...theme.mixins.toolbar,
  },
  content: {
    flexGrow: 1,
    padding: theme.spacing(3),
  },


}));

export default function MiniDrawer() {
  const classes = useStyles();
  const theme = useTheme();
  const [open, setOpen] = React.useState(false);

  const handleDrawerOpen = () => {
    setOpen(true);
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
          [classes.appBarShift]: open,
        })}
      >
        <Toolbar>
          <IconButton
            color="inherit"
            aria-label="open drawer"
            onClick={handleDrawerOpen}
            edge="start"
            className={clsx(classes.menuButton, {
              [classes.hide]: open,
            })}
          >
            <MenuIcon />
          </IconButton>
          <Typography variant="h6" style={{color:"#9d9d9d"}} noWrap>
            Soploon
          </Typography>
          <img src={Soploon} className={classes.image} / >

        </Toolbar>
      </AppBar>
      <BrowserRouter>

      <Drawer
        variant="permanent"
        className={clsx(classes.drawer, {
          [classes.drawerOpen]: open,
          [classes.drawerClose]: !open,
        })}
        classes={{
          paper: clsx({
            [classes.drawerOpen]: open,
            [classes.drawerClose]: !open,
          }),
        }}
      >
        <div className={classes.toolbar}>
          <IconButton onClick={handleDrawerClose}>
            {theme.direction === 'rtl' ? <ChevronRightIcon /> : <ChevronLeftIcon />}
          </IconButton>
        </div>
        <Divider />
        <List>
            <ListItem button component={Link} to={"/users"}>
              <ListItemIcon><PersonIcon /></ListItemIcon>
              <ListItemText primary="Usuarios" />
            </ListItem>
            <ListItem button component={Link} to={"/projects"}>
              <ListItemIcon><FolderIcon /></ListItemIcon>
              <ListItemText primary="Proyectos" />
            </ListItem>
            <ListItem button component={Link} to={"/rules"}>
              <ListItemIcon><TocIcon /></ListItemIcon>
              <ListItemText primary="Reglas activas" />
            </ListItem>
        </List>
        <Divider />

        <List>
            <ListItem button component={Link} to={"/predicates"}>
              <ListItemIcon><LibraryBooksIcon /></ListItemIcon>
              <ListItemText primary="Predicados activos" />
            </ListItem>
            <ListItem button component={Link} to={"/addPredicates"}>
              <ListItemIcon><LibraryAddIcon /></ListItemIcon>
              <ListItemText primary="Agregar predicado" />
            </ListItem>
        </List>
        <Divider />
      </Drawer>
      <main className={classes.content}>
        <div className={classes.toolbar} />
        <Switch>
          <Route exact path="/" render={() => <div>Home Page</div>} />
          <Route path="/users" render={() => <div> <TableUsers/></div>} />
          <Route path="/projects" render={() => <div> <TableProjects/></div>} />
          <Route path="/rules" render={() => <div> <Rules/></div>} />
          <Route path="/predicates" render={() => <div> <Predicates/></div>} />
          <Route path="/addPredicates" render={() => <div> <AddPredicate/></div>} />
          <Route  render={() => <div> <Result
            status="404"
            title="404"
            subTitle="La página que intenta visitar no existe"
          /> </div>} />
        </Switch>
      </main>
       </BrowserRouter>
    </div>
  );
}
