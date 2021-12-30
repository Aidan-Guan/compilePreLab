declare void @putarray(i32,i32*)
declare i32 @getarray(i32*)
@n = dso_local global i32 0
define dso_local i32 @swap(i32* %x0, i32 %x1, i32 %x2){
	%x4 = alloca i32*
	store i32* %x0, i32* * %x4
	%x5 = alloca i32
	store i32 %x1, i32* %x5
	%x6 = alloca i32
	store i32 %x2, i32* %x6
	%x7 = alloca i32
	%x8 = load i32, i32* %x5
	%x9 = load i32*, i32* * %x4
	%x10 = getelementptr i32, i32* %x9, i32 %x8
	%x11 = load i32, i32* %x10
	store i32 %x11, i32* %x7
	%x12 = load i32, i32* %x5
	%x13 = load i32*, i32* * %x4
	%x14 = getelementptr i32, i32* %x13, i32 %x12
	%x15 = load i32, i32* %x6
	%x16 = load i32*, i32* * %x4
	%x17 = getelementptr i32, i32* %x16, i32 %x15
	%x18 = load i32, i32* %x17
	store i32 %x18, i32* %x14
	%x19 = load i32, i32* %x6
	%x20 = load i32*, i32* * %x4
	%x21 = getelementptr i32, i32* %x20, i32 %x19
	%x22 = load i32, i32* %x7
	store i32 %x22, i32* %x21
	%x23 = add i32 0, 0
	ret i32 %x23
}
define dso_local i32 @heap_ajust(i32* %x0, i32 %x1, i32 %x2){
	%x4 = alloca i32*
	store i32* %x0, i32* * %x4
	%x5 = alloca i32
	store i32 %x1, i32* %x5
	%x6 = alloca i32
	store i32 %x2, i32* %x6
	%x7 = alloca i32
	%x8 = load i32, i32* %x5
	store i32 %x8, i32* %x7
	%x9 = alloca i32
	%x10 = load i32, i32* %x7
	%x11 = add i32 0, 2
	%x12 = mul i32 %x10, %x11
	%x13 = add i32 0, 1
	%x14 = add i32 %x12, %x13
	store i32 %x14, i32* %x9
	br label %block0

block0:
	%x15 = load i32, i32* %x9
	%x16 = load i32, i32* %x6
	%x17 = add i32 0, 1
	%x18 = add i32 %x16, %x17
	%x19 = icmp slt i32 %x15, %x18
	br i1 %x19, label %block1, label %block2

block1:
	%x20 = load i32, i32* %x9
	%x21 = load i32, i32* %x6
	%x22 = icmp slt i32 %x20, %x21
	br i1 %x22, label %block5, label %block4

block5:
	%x23 = load i32, i32* %x9
	%x24 = load i32*, i32* * %x4
	%x25 = getelementptr i32, i32* %x24, i32 %x23
	%x26 = load i32, i32* %x25
	%x27 = load i32, i32* %x9
	%x28 = add i32 0, 1
	%x29 = add i32 %x27, %x28
	%x30 = load i32*, i32* * %x4
	%x31 = getelementptr i32, i32* %x30, i32 %x29
	%x32 = load i32, i32* %x31
	%x33 = icmp slt i32 %x26, %x32
	br i1 %x33, label %block3, label %block4

block3:
	%x34 = load i32, i32* %x9
	%x35 = add i32 0, 1
	%x36 = add i32 %x34, %x35
	store i32 %x36, i32* %x9
	br label %block4

block4:
	%x37 = load i32, i32* %x7
	%x38 = load i32*, i32* * %x4
	%x39 = getelementptr i32, i32* %x38, i32 %x37
	%x40 = load i32, i32* %x39
	%x41 = load i32, i32* %x9
	%x42 = load i32*, i32* * %x4
	%x43 = getelementptr i32, i32* %x42, i32 %x41
	%x44 = load i32, i32* %x43
	%x45 = icmp sgt i32 %x40, %x44
	br i1 %x45, label %block6, label %block7

block6:
	%x46 = add i32 0, 0
	ret i32 %x46

block7:
	%x47 = load i32*, i32* * %x4
	%x48 = load i32, i32* %x7
	%x49 = load i32, i32* %x9
	%x50 = call i32 @swap(i32* %x47, i32 %x48, i32 %x49)
	store i32 %x50, i32* %x7
	%x51 = load i32, i32* %x9
	store i32 %x51, i32* %x7
	%x52 = load i32, i32* %x7
	%x53 = add i32 0, 2
	%x54 = mul i32 %x52, %x53
	%x55 = add i32 0, 1
	%x56 = add i32 %x54, %x55
	store i32 %x56, i32* %x9
	br label %block8

block8:
	br label %block0

block2:
	%x57 = add i32 0, 0
	ret i32 %x57
}
define dso_local i32 @heap_sort(i32* %x0, i32 %x1){
	%x3 = alloca i32*
	store i32* %x0, i32* * %x3
	%x4 = alloca i32
	store i32 %x1, i32* %x4
	%x5 = alloca i32
	%x6 = alloca i32
	%x7 = load i32, i32* %x4
	%x8 = add i32 0, 2
	%x9 = sdiv i32 %x7, %x8
	%x10 = add i32 0, 1
	%x11 = sub i32 %x9, %x10
	store i32 %x11, i32* %x5
	br label %block0

block0:
	%x12 = load i32, i32* %x5
	%x13 = add i32 0, 1
	%x14 = sub i32 0, %x13
	%x15 = icmp sgt i32 %x12, %x14
	br i1 %x15, label %block1, label %block2

block1:
	%x16 = load i32, i32* %x4
	%x17 = add i32 0, 1
	%x18 = sub i32 %x16, %x17
	store i32 %x18, i32* %x6
	%x19 = load i32*, i32* * %x3
	%x20 = load i32, i32* %x5
	%x21 = load i32, i32* %x6
	%x22 = call i32 @heap_ajust(i32* %x19, i32 %x20, i32 %x21)
	store i32 %x22, i32* %x6
	%x23 = load i32, i32* %x5
	%x24 = add i32 0, 1
	%x25 = sub i32 %x23, %x24
	store i32 %x25, i32* %x5
	br label %block0

block2:
	%x26 = load i32, i32* %x4
	%x27 = add i32 0, 1
	%x28 = sub i32 %x26, %x27
	store i32 %x28, i32* %x5
	br label %block3

block3:
	%x29 = load i32, i32* %x5
	%x30 = add i32 0, 0
	%x31 = icmp sgt i32 %x29, %x30
	br i1 %x31, label %block4, label %block5

block4:
	%x32 = alloca i32
	%x33 = add i32 0, 0
	store i32 %x33, i32* %x32
	%x34 = load i32*, i32* * %x3
	%x35 = load i32, i32* %x32
	%x36 = load i32, i32* %x5
	%x37 = call i32 @swap(i32* %x34, i32 %x35, i32 %x36)
	store i32 %x37, i32* %x6
	%x38 = load i32, i32* %x5
	%x39 = add i32 0, 1
	%x40 = sub i32 %x38, %x39
	store i32 %x40, i32* %x6
	%x41 = load i32*, i32* * %x3
	%x42 = load i32, i32* %x32
	%x43 = load i32, i32* %x6
	%x44 = call i32 @heap_ajust(i32* %x41, i32 %x42, i32 %x43)
	store i32 %x44, i32* %x6
	%x45 = load i32, i32* %x5
	%x46 = add i32 0, 1
	%x47 = sub i32 %x45, %x46
	store i32 %x47, i32* %x5
	br label %block3

block5:
	%x48 = add i32 0, 0
	ret i32 %x48
}
define dso_local i32 @main(){
	%x1 = add i32 0, 10
	%x2 = alloca [10 x i32]
	%x3 = getelementptr  [10 x i32],  [10 x i32]* %x2, i32 0, i32 0
	%x4 = call i32 @getarray(i32* %x3)
	store i32 %x4, i32* @n
	%x5 = alloca i32
	%x6 = add i32 0, 0
	store i32 %x6, i32* %x5
	%x7 = getelementptr  [10 x i32],  [10 x i32]* %x2, i32 0, i32 0
	%x8 = load i32, i32* @n
	%x9 = call i32 @heap_sort(i32* %x7, i32 %x8)
	store i32 %x9, i32* %x5
	%x10 = load i32, i32* @n
	%x11 = getelementptr  [10 x i32],  [10 x i32]* %x2, i32 0, i32 0
	call void @putarray(i32 %x10, i32* %x11)
	%x12 = add i32 0, 0
	ret i32 %x12
}