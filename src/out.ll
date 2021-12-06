declare i32 @putint(i32)
@one = dso_local global i32 1
define dso_local i32 @main(){
	%x2 = alloca i32
	%x3 = add i32 0, 2
	%x4 = add i32 0, 2
	%x5 = add i32 %x3, %x4
	store i32 %x5, i32* %x2
	br label %block1

block1:
	%x6 = load i32, i32* %x2
	%x7 = add i32 0, 75
	%x8 = icmp slt i32 %x6, %x7
	br i1 %x8, label %block2, label %block3

block2:
	%x9 = alloca i32
	%x10 = add i32 0, 42
	store i32 %x10, i32* %x9
	br label %block4

block4:
	%x11 = load i32, i32* %x2
	%x12 = add i32 0, 100
	%x13 = icmp slt i32 %x11, %x12
	br i1 %x13, label %block5, label %block6

block5:
	%x14 = load i32, i32* %x2
	%x15 = load i32, i32* %x9
	%x16 = add i32 %x14, %x15
	store i32 %x16, i32* %x2
	%x17 = load i32, i32* %x2
	%x18 = add i32 0, 99
	%x19 = icmp sgt i32 %x17, %x18
	br i1 %x19, label %block7, label %block8

block7:
	%x20 = alloca i32
	%x21 = load i32, i32* %x9
	%x22 = add i32 0, 2
	%x23 = mul i32 %x21, %x22
	store i32 %x23, i32* %x20
	%x24 = load i32, i32* @one
	%x25 = add i32 0, 1
	%x26 = icmp eq i32 %x24, %x25
	br i1 %x26, label %block9, label %block10

block9:
	%x27 = load i32, i32* %x20
	%x28 = add i32 0, 2
	%x29 = mul i32 %x27, %x28
	store i32 %x29, i32* %x2
	br label %block10

block10:
	br label %block8

block8:
	br label %block4

block6:
	br label %block1

block3:
	%x30 = load i32, i32* %x2
	%x31 = call i32 @putint(i32 %x30)
	%x32 = add i32 0, 0
	ret i32 %x32
}declare i32 @putint(i32)
@one = dso_local global i32 1
define dso_local i32 @main(){
	%x2 = alloca i32
	%x3 = add i32 0, 2
	%x4 = add i32 0, 2
	%x5 = add i32 %x3, %x4
	store i32 %x5, i32* %x2
	br label %block1

block1:
	%x6 = load i32, i32* %x2
	%x7 = add i32 0, 75
	%x8 = icmp slt i32 %x6, %x7
	br i1 %x8, label %block2, label %block3

block2:
	%x9 = alloca i32
	%x10 = add i32 0, 42
	store i32 %x10, i32* %x9
	br label %block4

block4:
	%x11 = load i32, i32* %x2
	%x12 = add i32 0, 100
	%x13 = icmp slt i32 %x11, %x12
	br i1 %x13, label %block5, label %block6

block5:
	%x14 = load i32, i32* %x2
	%x15 = load i32, i32* %x9
	%x16 = add i32 %x14, %x15
	store i32 %x16, i32* %x2
	%x17 = load i32, i32* %x2
	%x18 = add i32 0, 99
	%x19 = icmp sgt i32 %x17, %x18
	br i1 %x19, label %block7, label %block8

block7:
	%x20 = alloca i32
	%x21 = load i32, i32* %x9
	%x22 = add i32 0, 2
	%x23 = mul i32 %x21, %x22
	store i32 %x23, i32* %x20
	%x24 = load i32, i32* @one
	%x25 = add i32 0, 1
	%x26 = icmp eq i32 %x24, %x25
	br i1 %x26, label %block9, label %block10

block9:
	%x27 = load i32, i32* %x20
	%x28 = add i32 0, 2
	%x29 = mul i32 %x27, %x28
	store i32 %x29, i32* %x2
	br label %block10

block10:
	br label %block8

block8:
	br label %block4

block6:
	br label %block1

block3:
	%x30 = load i32, i32* %x2
	%x31 = call i32 @putint(i32 %x30)
	%x32 = add i32 0, 0
	ret i32 %x32
}declare i32 @putint(i32)
@one = dso_local global i32 1
define dso_local i32 @main(){
	%x2 = alloca i32
	%x3 = add i32 0, 2
	%x4 = add i32 0, 2
	%x5 = add i32 %x3, %x4
	store i32 %x5, i32* %x2
	br label %block1

block1:
	%x6 = load i32, i32* %x2
	%x7 = add i32 0, 75
	%x8 = icmp slt i32 %x6, %x7
	br i1 %x8, label %block2, label %block3

block2:
	%x9 = alloca i32
	%x10 = add i32 0, 42
	store i32 %x10, i32* %x9
	br label %block4

block4:
	%x11 = load i32, i32* %x2
	%x12 = add i32 0, 100
	%x13 = icmp slt i32 %x11, %x12
	br i1 %x13, label %block5, label %block6

block5:
	%x14 = load i32, i32* %x2
	%x15 = load i32, i32* %x9
	%x16 = add i32 %x14, %x15
	store i32 %x16, i32* %x2
	%x17 = load i32, i32* %x2
	%x18 = add i32 0, 99
	%x19 = icmp sgt i32 %x17, %x18
	br i1 %x19, label %block7, label %block8

block7:
	%x20 = alloca i32
	%x21 = load i32, i32* %x9
	%x22 = add i32 0, 2
	%x23 = mul i32 %x21, %x22
	store i32 %x23, i32* %x20
	%x24 = load i32, i32* @one
	%x25 = add i32 0, 1
	%x26 = icmp eq i32 %x24, %x25
	br i1 %x26, label %block9, label %block10

block9:
	%x27 = load i32, i32* %x20
	%x28 = add i32 0, 2
	%x29 = mul i32 %x27, %x28
	store i32 %x29, i32* %x2
	br label %block10

block10:
	br label %block8

block8:
	br label %block4

block6:
	br label %block1

block3:
	%x30 = load i32, i32* %x2
	%x31 = call i32 @putint(i32 %x30)
	%x32 = add i32 0, 0
	ret i32 %x32
}