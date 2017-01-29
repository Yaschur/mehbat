var gulp = require("gulp");
var tslint = require("gulp-tslint");
var clean = require("gulp-clean");
var typescript = require("gulp-typescript");
var copy = require("gulp-copy");
var connect = require("gulp-connect");


var runSequence = require("run-sequence");

var appPath = "./src";
var tsPath = appPath + "/**/*.ts";
var staticPaths = [
	appPath + "/**/*.html", 
	appPath + "/**/*.css", 
	appPath + "/**/*.js"
];
var vendorPaths = [
	"./node_modules/@angular/core/bundles/core.umd.js",
	"./node_modules/@angular/common/bundles/common.umd.js",
	"./node_modules/@angular/compiler/bundles/compiler.umd.js",
	"./node_modules/@angular/platform-browser/bundles/platform-browser.umd.js",
	"./node_modules/@angular/platform-browser-dynamic/bundles/platform-browser-dynamic.umd.js",
	"./node_modules/@angular/http/bundles/http.umd.js",
	"./node_modules/@angular/router/bundles/router.umd.js",
	"./node_modules/@angular/forms/bundles/forms.umd.js",
	"./node_modules/rxjs/**/*.js",
	"./node_modules/core-js/client/shim.min.js",
	"./node_modules/zone.js/dist/zone.js",
	"./node_modules/systemjs/dist/system.src.js"
];

var destPath = "./dist";

gulp.task("tslint", () =>
	gulp.src(tsPath)
		.pipe(tslint({ formatter: "prose" }))
		.pipe(tslint.report())
);

gulp.task("clean", () =>
	gulp.src(destPath, { read: false })
		.pipe(clean())
);

gulp.task("copy", () =>
	gulp.src(staticPaths)
		.pipe(copy(destPath, { prefix: 1 }))

	//.pipe(connect.reload()); with timeout
);

gulp.task("copyv", () =>
	gulp.src(vendorPaths)
		.pipe(copy(destPath))
);

gulp.task("tscompile", () => {
	var tsProject = typescript.createProject("./tsconfig.json");
	var tsResult = gulp.src(tsPath)
		.pipe(tsProject());

	return tsResult.js
		.pipe(gulp.dest(destPath));
	//.pipe(connect.reload());
})

gulp.task("build", (done) =>
	//runSequence("dev:tslint", "dev:clean", "dev:compile:sass", "dev:copy:vendor-js", "dev:compile:typescript", done)
	runSequence("tslint", "clean", "copy", "copyv", "tscompile", done)
);

gulp.task('serve', function() {
	connect.server({
		//port: '8080',
		root: destPath,
		//fallback: paths.dev.serverFallback,
		livereload: true
	});
});

gulp.task("default", (done) =>
	//runSequence("build", "serve", "watch", done)
	runSequence("build", "serve", done)
);