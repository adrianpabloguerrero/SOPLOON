import React from 'react';
import PackageExplorer from './package-explorer';
import SourceFile from './source-file';
import Paper from '@material-ui/core/Paper';
import Grid from '@material-ui/core/Grid';

class CorrectionShower extends React.Component {
		
	constructor(props) {
		super(props);
		this.state = { 'show': props.correction != null, 'correction': props.correction, 'sourceFile': null };
	}
		
	static getDerivedStateFromProps(props, state) {
		const { correction } = props;
		if (correction !== state.correction)
			return{ 'show': correction != null, 'correction': correction, 'sourceFile': null };
		else
			return state;
	}
	
	selectSourceFile = (sourceFile) => {
		this.setState({'correction': this.state.correction, 'show': this.state.show, 'sourceFile': sourceFile});
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
							<SourceFile language='java' sourceFile={this.state.sourceFile} />
						</Paper>
					</Grid>
				</Grid>
			)
		else
			return null;
	}
	
}

export default CorrectionShower;

