define dso_local i32 @main(){
	%x1 = alloca i32
	br label %block0

block0:
	%x2 = load i32, i32* %x1
	%x3 = add i32 0, 2
	%x4 = icmp slt i32 %x2, %x3
	br i1 %x4, label %block1, label %block2

block1:
	%x5 = load i32, i32* %x1
	%x6 = add i32 0, 1
	%x7 = icmp eq i32 %x5, %x6
	br i1 %x7, label %block3, label %block4

block3:
	br label %block2

block4:
	br label %block0

block2:
	%x8 = add i32 0, 0
	ret i32 %x8
}