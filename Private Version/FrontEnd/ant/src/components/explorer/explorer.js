import React, { useEffect } from 'react';
import Paper from '@material-ui/core/Paper';
import Grid from '@material-ui/core/Grid';
import ExplorerNavigationBar from './explorer-navigation-bar';
import ExplorerVerticalList from './explorer-vertical-list';
import CorrectionShower from './correction-shower';
import Style from '../utils/style';

import Axios from 'axios';

export default function Explorer() {

	const style = Style();
	
	const [state,setState] = React.useState({ user: null, project: null, correction: null});

	const [users, setUsers] = React.useState([]);
	const [projects, setProjects] = React.useState([]);
	const [corrections, setCorrections] = React.useState([]);

	
	const deselectUser = () => {
		setState({user: null, project: null, correction: null});
	}
	
	const selectUser = (user) => {
		setState({user: user, project: null, correction: null});
		getProjects(user);
	}
	
	const deselectProject = () => {
		setState({user: state.user, project: null, correction: null});
	}

	const selectProject = (project) => {
		setState({user: state.user, project: project, correction: null});
		getCorrections(project);
	}
	
	const deselectCorrection = () => {
		setState({user: state.user, project: state.project, correction: null});
	}
	
	const selectCorrection = (correction) => {
		setState({user: state.user, project: state.project, correction: correction});
	}
			
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
				
	const navigationBarActions = () => {
		return {'selectInit': deselectUser, 'selectUser': deselectProject, 'selectProject': deselectCorrection};
	}
	
	const navigationBarData = () => {
		return state;
	}

	const verticalListData = () => {
		return {'currentUser': state.user, 'currentProject': state.project, 'currentCorrection': state.correction, users, projects, corrections};
	}
	
	const verticalListActions = () => {
		return {selectUser, selectProject, selectCorrection};
	}

	useEffect(() => { getUsers(); }, []);

	return (
		<div>
			<Grid container spacing={3}>
				<Grid item xs={12}>
					<Paper className={style.paper}>
					  <ExplorerNavigationBar style={style} data={navigationBarData()} actions={navigationBarActions()}/>
					</Paper>
				</Grid>
			</Grid>
			{ 	
				state.correction == null ? 
				(
					<Grid container spacing={3}>
						<Grid item xs={3}>
							<Paper className={style.paper}>
								<ExplorerVerticalList data={verticalListData()} actions={verticalListActions()}/>
							</Paper>
						</Grid>
					</Grid>) :
				(<CorrectionShower style={style} correction={state.correction} />)
			}
			
		</div>
	);

}
