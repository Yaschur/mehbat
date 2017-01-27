var gulp = require("gulp");
var tslint = require("gulp-tslint");

var runSequence = require('run-sequence');

var appPath = "./app";

gulp.task("tslint", () => 
	gulp.src(appPath + "/**/*.ts")
		.pipe(tslint({ formatter: "prose" }))
		.pipe(tslint.report())
);

gulp.task('build', (done) => 
	//runSequence('dev:tslint', 'dev:clean', 'dev:compile:sass', 'dev:copy:vendor-js', 'dev:compile:typescript', done)
	runSequence('tslint', done)
);

gulp.task('default', (done) =>
	//runSequence('build', 'serve', 'watch', done)
	runSequence('build', done)
);