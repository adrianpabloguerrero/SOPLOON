
const ExplorerUtils = {};

ExplorerUtils.structureCorrection = (correction) => {
	
	if (correction != null) {
		let folders = {childrens: []};

		correction.code.forEach(sourceFile => {
			var paths = sourceFile.path.split("/");
			paths = paths.slice(1,paths.length);
			
			var partialPath = "";
			
			var currentFolder = folders;
			paths.forEach(path => {
				var current = null;
				partialPath = partialPath + "/" + path;
				currentFolder.childrens.forEach(node => {
					if (node.name === path)
						current = node;
				});
				
				if (current == null) {
					current = {};
					current.name = path;
					current.id = partialPath;
					current.childrens = [];
					currentFolder.childrens.push(current);
				}

				currentFolder = current;
				
			});
			
			currentFolder.source = sourceFile.code;

		});
		
		return folders.childrens;
	
	}
	
}

ExplorerUtils.simplifiedStructureCorrection = (correction) => {
	
	if (correction !== null) {

		var folders = ExplorerUtils.structureCorrection(correction);
		
		var toAnalyze = new Array();

		folders.forEach(folder => {
			toAnalyze.push(folder);
		});

		
		for (var index = 0; index < toAnalyze.length; index++) { 
			var folder = toAnalyze[index];
				
			if (folder.source === undefined) {
				if (folder.childrens.length == 1) {
					var children = folder.childrens[0];
					folder.id = children.id;
					folder.childrens = children.childrens;
					folder.name = folder.name + '/' + children.name;
					index--;
				} else {
					folder.childrens.forEach(children => {
						toAnalyze.push(children);
					});
					index++;
				}
			} else {
				index++;
			}
		}
				
		return folders;
		
	}
	
}



export default ExplorerUtils;
