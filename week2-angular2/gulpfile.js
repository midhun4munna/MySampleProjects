var gulp = require('gulp');
var tsc = require('gulp-typescript');
var tsProject = tsc.createProject('tsconfig.json');
var config = require('./gulp.config')();
var uglify = require('gulp-uglify');
var concat = require('gulp-concat');

gulp.task('compile-minify',function(){
	var sourceTsFiles = [
		config.allTs
	];
	
	var tsResult = gulp
					.src(sourceTsFiles)
					.pipe(tsProject());
					
	return tsResult.js
			.pipe(gulp.dest(config.tsOutputPath))
			.pipe(uglify())
			.pipe(gulp.dest('app/'));
	
});


gulp.task('minify',function(){
	var sourceTsFiles = [
		config.allTs
	];
	
	return tsResult = gulp
					.src('app/*.js')
					.pipe(uglify())
					.pipe(gulp.dest('app/'));
	
});