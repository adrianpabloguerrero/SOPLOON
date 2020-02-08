import React, { useEffect, useState } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import List from '@material-ui/core/List';
import Paper from '@material-ui/core/Paper';
import Grid from '@material-ui/core/Grid';
import ListItem  from '@material-ui/core/ListItem';
import ListItemText  from '@material-ui/core/ListItemText';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListSubheader from '@material-ui/core/ListSubheader';
import Breadcrumbs from '@material-ui/core/Breadcrumbs';
import Typography from '@material-ui/core/Typography';
import Link from '@material-ui/core/Link';
import NavigateNextIcon from '@material-ui/icons/NavigateNext';

import Axios from 'axios';

const useStyles = makeStyles(theme => ({
  root: {
    flexGrow: 1,
  },
  paper: {
    padding: theme.spacing(2),
    textAlign: 'center',
    color: theme.palette.text.secondary,
  },
}));

export default function Explorer() {

	const classes = useStyles();
	
	const [users, setUsers] = React.useState([]);
	const [projects, setProjects] = React.useState([]);
	const [corrections, setCorrections] = React.useState([]);

	const [state,setState] = React.useState({ user: null, project: null, correction: null});
	
	const structureUsers = () => {
		return users.map(user => (
		  <ListItem key={user.id} onClick={ () => selectUser(user) } button> <ListItemText primary={user.name} /> </ListItem>
		));
	};

	const structureProjects = () => {
		return projects.map(project => (
		  <ListItem key={project.id} onClick={ () => selectProject(project) } button>  <ListItemText primary={project.name} /> </ListItem>
		));
	};
	
	const structureCorrections = () => {
		return corrections.map(correction => (
		  <ListItem key={correction.date} button> <ListItemText primary={ new Date(correction.date).toLocaleString() } /> </ListItem>
		));
	};
	
	const selectUser = (user) => {
		setState({user: user, project: null, correction: null});
		if (user != null)
			getProjects(user);
	}

	const selectProject = (project) => {
		setState({user: state.user, project: project, correction: null});
		if (project != null)
			getCorrections(project);
	}
	
	const selectCorrection = (correction) => {
		setState({user: state.user, project: project, correction: correction});
	}
	
	const showUsers = () => {
		return (state.user == null);
	};

	const showProjects = () => {
		return (state.user != null && state.project == null);
	};
	
	const showCorrections = () => {
		return (state.user != null && state.project != null && state.correction == null);
	};
	
	const userName = () => {
		return state.user != null ? state.user.name : '';
	};
	
	const projectName = () => {
		return state.project != null ? state.project.name : '';
	};
	
	const getUsers = () => {
		let url = 'http://localhost:8080/soploon/api/users/';
		Axios.get(url)
			.then(response => {
				setUsers(response.data);
			})
			.catch(response => {
			});
	};

	const getProjects = (user) => {
		let url = 'http://localhost:8080/soploon/api/users/' + user.id + '/projects/';
		Axios.get(url)
			.then(response => {
				setProjects(response.data);
			})
			.catch(response => {
			});
	};

	const getCorrections = (project) => {
		let url = 'http://localhost:8080/soploon/api/users/' + project.userId + '/projects/' + project.id + '/corrections/';
		Axios.get(url)
			.then(response => {
				setCorrections(response.data);
			})
			.catch(response => {
			});
	};
	
	const getUserNavigation = () =>  {
		if (state.user != null)			
			return (<Link color="inherit" onClick={() => selectProject(null)} > { userName() } </Link>)
		else 
			return null;
	}
	
	const getProjectNavigation = () =>  {
		if (state.project != null)			
			return (<Link color="inherit" onClick={() => selectCorrection(null)} > { projectName() } </Link>)
		else 
			return null;
	}
	
	useEffect(() => { getUsers(); }, []);

	return (
		<div>
			<Grid container spacing={3}>
				<Grid item xs={12}>
					<Paper className={classes.paper}>
					  <Breadcrumbs separator={<NavigateNextIcon fontSize="small" />} aria-label="breadcrumb">
					  	<Link color="inherit" onClick={ () => selectUser(null)}> Inicio </Link>
						{getUserNavigation()}
						{getProjectNavigation()}
					  </Breadcrumbs>
					</Paper>
				</Grid>
				<Grid item xs={3}>
					<Paper className={classes.paper}>
						<List hidden={!showUsers()} subheader={ <div><ListSubheader component="div"> Usuarios </ListSubheader></div> }>
							{structureUsers(users)}
						</List>
						<List hidden={!showProjects()} subheader={<div> <ListSubheader component="div"> { "Projectos de " + userName() } </ListSubheader></div>}>
							{structureProjects(projects)}
						</List>
						<List hidden={!showCorrections()} subheader={<div> <ListSubheader component="div"> { "Correcciones de " + projectName() } </ListSubheader></div>}>
							{structureCorrections(projects)}
						</List>
					</Paper>

				</Grid>
			</Grid>
		</div>
	);

}
