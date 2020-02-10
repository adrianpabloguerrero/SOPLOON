import React from 'react';
import NavigationBar from '../utils/navigation-bar';
import Grid from '@material-ui/core/Grid';
import DownloadIcon from '@material-ui/icons/SystemUpdateAlt';
import Tooltip from '@material-ui/core/Tooltip';
import IconButton from '@material-ui/core/IconButton';
import Box from '@material-ui/core/Box';
import Button from '@material-ui/core/Button';

class ExplorerNavigationBar extends React.Component {
		
	shouldComponentUpdate(nextProps, nextState) {
		return (nextProps.data !== this.props.data);
	}
  
	structure = (data, actions) => {
		const {user, project, correction} = data;
		const {selectInit, selectUser, selectProject} = actions;

		var items = [{'text': 'Inicio', 'action': selectInit }];
		
		if (user != null) {
			items.push({'text': user.name, 'action': selectUser});
			if (project != null) {
				items.push({'text': project.name, 'action': selectProject});
				if (correction != null) {
					items.push({'text': new Date(correction.date).toLocaleString()});
				}
			}
		}
		
		return items;
	}
	
	render() {
		var items = this.structure(this.props.data, this.props.actions);
		
		return (
			<Grid container>
				<Grid item xs={8} sm={10} alignContent='center' alignItems='center'>
						<NavigationBar items={items}/>
				</Grid>
				{
					this.props.data.correction != null && 
					(<Grid style={{textAlign: 'right'}} item xs={4} sm={2}>
						<Tooltip title="Descargar proyecto" aria-label="download">
							<Button color='inherit' style={{textTransform:'none'}}endIcon= {<DownloadIcon/>}>
								Descargar
							</Button>
						</Tooltip>
					</Grid>)
				}
			</Grid>
		)
	}
}

export default ExplorerNavigationBar;

