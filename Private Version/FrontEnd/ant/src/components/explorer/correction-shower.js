import React from 'react';
import PackageExplorer from './package-explorer';
import SourceFiles from './source-files';
import Paper from '@material-ui/core/Paper';
import Grid from '@material-ui/core/Grid';

class CorrectionShower extends React.Component {
		
	constructor(props) {
		super(props);
		this.state = { 'show': props.correction != null, 'correction': props.correction, 'sourceFiles': [] };
	}

	
	static getDerivedStateFromProps(props, state) {
		const { correction } = props;
		if (correction !== state.correction)
			return{ 'show': correction != null, 'correction': correction, 'sourceFiles': [] };
		else
			return state;
	}
	
	selectSourceFile = (sourceFile) => {
		var array = this.state.sourceFiles;
		if (!array.includes(sourceFile)) { 
			array.push(sourceFile);
			this.setState({'correction': this.state.correction, 'show': this.state.show, 'sourceFiles': array });
		}
	}
	
	closeSourceFile = (sourceFile) => {
		var array = this.state.sourceFiles;
		var index = array.indexOf(sourceFile);
		if (index !== -1) { 
			array.splice(index, 1);
			this.setState({'correction': this.state.correction, 'show': this.state.show, 'sourceFiles': array });
		}
	}
	
	render() {
		const { style } = this.props; 
		
		if (this.state.show)
			return (
				<Grid container spacing={3}>
					<Grid item xs={3}>
						<Paper className={style.paper}>
							<PackageExplorer correction={this.state.correction} action={this.selectSourceFile} />
						</Paper>
					</Grid>
					<Grid item xs={9}>
						<Paper className={style.paper}>
							<SourceFiles style={style} language='java' closeAction={this.closeSourceFile} sourceFiles={this.state.sourceFiles} />
						</Paper>
					</Grid>
				</Grid>
			)
		else
			return null;
	}
	
}

export default CorrectionShower;

