declare i32 @putint()
@a = dso_local global i32 6
@b = dso_local global i32 1
define dso_local i32 @main(){
	%x4 = alloca i32
	%x5 = load i32, i32* @b
	store i32 %x5, i32* %x4
	%x6 = alloca i32
	%x7 = add i32 0, 8
	store i32 %x7, i32* %x6
	%x8 = call i32 @putint()
	%x9 = add i32 0, 0
	ret i32 %x9
}