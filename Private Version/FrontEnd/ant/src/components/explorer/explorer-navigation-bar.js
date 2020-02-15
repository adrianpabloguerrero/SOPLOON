import React from 'react';
import NavigationBar from '../utils/navigation-bar';
import Grid from '@material-ui/core/Grid';
import DownloadIcon from '@material-ui/icons/SystemUpdateAlt';
import Tooltip from '@material-ui/core/Tooltip';
import Button from '@material-ui/core/Button';
import JSZip from 'jszip';
import ExplorerUtils from './explorer-utils.js'
import FileSaver from 'file-saver';
import Hidden from '@material-ui/core/Hidden';

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
	
	zipFolders = (folders, zip) => {
	
		folders.forEach((folder) => {
			if (folder.childrens.length > 0) {
				var subFolder = zip.folder(folder.name)
				this.zipFolders(folder.childrens, subFolder);
			} else {
				zip.file(folder.name, folder.source);
			}
		});
	}
	
	downloadProject = (correction) => {

		const { project } = this.props.data;

		var zip = new JSZip();

		var folders = ExplorerUtils.structureCorrection(correction);
		
		this.zipFolders(folders, zip);
		
		zip.generateAsync({type:"blob"}).then(function (blob) {
			FileSaver.saveAs(blob, project.name + "-" + correction.date);
		});
		
	}
	
	render() {
		var items = this.structure(this.props.data, this.props.actions);
		
		return (
			<Grid container>
				<Grid item xs={10} sm={11}>
						<NavigationBar items={items}/>
				</Grid>
				{
					this.props.data.correction != null && 
					(<Grid style={{ 'position': 'relative'}} item xs={2} sm={1}>
						<Tooltip style={{ 'position': 'absolute', 'right': '5px'}} title="Descargar proyecto" aria-label="download">
							<span>
								<Button disabled={this.props.data.correction.code === undefined}  onClick={ () => this.downloadProject(this.props.data.correction)} color='inherit' style={{textTransform:'none'}}>
									<Hidden smDown> <span style={{ 'marginRight': '5px'}}>Descargar</span> </Hidden>
									<DownloadIcon/>
								</Button>
							</span>
						</Tooltip>
					</Grid>)
				}
			</Grid>
		)
	}
}

export default ExplorerNavigationBar;

