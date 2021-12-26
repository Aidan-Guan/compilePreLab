declare void @putint(i32)
@N = dso_local global i32 -1
@arr = dso_local global [6 x i32] [ i32 1, i32 2, i32 33, i32 4, i32 5, i32 6]
define dso_local i32 @main(){
	%x9 = alloca i32
	%x10 = add i32 0, 0
	store i32 %x10, i32* %x9
	%x11 = alloca i32
	%x12 = add i32 0, 0
	store i32 %x12, i32* %x11
	br label %block1

block1:
	%x13 = load i32, i32* %x9
	%x14 = add i32 0, 5
	%x15 = icmp slt i32 %x13, %x14
	br i1 %x15, label %block2, label %block3

block2:
	%x16 = load i32, i32* %x11
	%x17 = load i32, i32* %x9
	%x18 = getelementptr  [6 x i32],  [6 x i32]* @arr, i32 0, i32 %x17
	%x19 = load i32, i32* %x18
	%x20 = add i32 %x16, %x19
	store i32 %x20, i32* %x11
	%x21 = load i32, i32* %x9
	%x22 = add i32 0, 1
	%x23 = add i32 %x21, %x22
	store i32 %x23, i32* %x9
	br label %block1

block3:
	%x24 = load i32, i32* %x11
	call void @putint(i32 %x24)
	%x25 = add i32 0, 0
	ret i32 %x25
}declare void @putint(i32)
define dso_local i32 @main(){
	%x1 = add i32 0, 1
	%2 = sub i32 0, %1
	%x3 = alloca [-1 x i32]
	%x4 = add i32 0, 0
	%x5 = getelementptr  [-1 x i32],  [-1 x i32]* %x3, i32 0, i32 %x4
	%x6 = load i32, i32* %x5
	call void @putint(i32 %x6)
	%x7 = add i32 0, 0
	ret i32 %x7
}declare void @putint(i32)
define dso_local i32 @main(){
	%x1 = add i32 0, 1
	%2 = sub i32 0, %1
	%x3 = alloca [-1 x i32]
	%x4 = add i32 0, 0
	%x5 = getelementptr  [-1 x i32],  [-1 x i32]* %x3, i32 0, i32 %x4
	%x6 = load i32, i32* %x5
	call void @putint(i32 %x6)
	%x7 = add i32 0, 0
	ret i32 %x7
}declare void @putint(i32)
define dso_local i32 @main(){
	%x1 = add i32 0, 1
	%2 = sub i32 0, %1
	%x3 = alloca [-1 x i32]
	%x4 = add i32 0, 0
	%x5 = getelementptr  [-1 x i32],  [-1 x i32]* %x3, i32 0, i32 %x4
	%x6 = load i32, i32* %x5
	call void @putint(i32 %x6)
	%x7 = add i32 0, 0
	ret i32 %x7
}declare void @putint(i32)
define dso_local i32 @main(){
	%x1 = add i32 0, 1
	%2 = sub i32 0, %1
	%x3 = alloca [-1 x i32]
	%x4 = add i32 0, 0
	%x5 = getelementptr  [-1 x i32],  [-1 x i32]* %x3, i32 0, i32 %x4
	%x6 = load i32, i32* %x5
	call void @putint(i32 %x6)
	%x7 = add i32 0, 0
	ret i32 %x7
}declare void @putint(i32)
define dso_local i32 @main(){
	%x1 = add i32 0, 1
	%x2 = sub i32 0, %x1
	%x3 = alloca [-1 x i32]
	%x4 = add i32 0, 0
	%x5 = getelementptr  [-1 x i32],  [-1 x i32]* %x3, i32 0, i32 %x4
	%x6 = load i32, i32* %x5
	call void @putint(i32 %x6)
	%x7 = add i32 0, 0
	ret i32 %x7
}