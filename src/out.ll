declare void @putint(i32)
@n = dso_local global i32 0
define dso_local i32 @bubblesort(i32* %x0){
	%x2 = alloca i32*
	store i32* %x0, i32* * %x2
	%x3 = alloca i32
	%x4 = alloca i32
	%x5 = add i32 0, 0
	store i32 %x5, i32* %x3
	br label %block0

block0:
	%x6 = load i32, i32* %x3
	%x7 = load i32, i32* @n
	%x8 = add i32 0, 1
	%x9 = sub i32 %x7, %x8
	%x10 = icmp slt i32 %x6, %x9
	br i1 %x10, label %block1, label %block2

block1:
	%x11 = add i32 0, 0
	store i32 %x11, i32* %x4
	br label %block3

block3:
	%x12 = load i32, i32* %x4
	%x13 = load i32, i32* @n
	%x14 = load i32, i32* %x3
	%x15 = sub i32 %x13, %x14
	%x16 = add i32 0, 1
	%x17 = sub i32 %x15, %x16
	%x18 = icmp slt i32 %x12, %x17
	br i1 %x18, label %block4, label %block5

block4:
	%x19 = load i32, i32* %x4
	%x20 = load i32*, i32* * %x2
	%x21 = getelementptr i32, i32* %x20, i32 %x19
	%x22 = load i32, i32* %x21
	%x23 = load i32, i32* %x4
	%x24 = add i32 0, 1
	%x25 = add i32 %x23, %x24
	%x26 = load i32*, i32* * %x2
	%x27 = getelementptr i32, i32* %x26, i32 %x25
	%x28 = load i32, i32* %x27
	%x29 = icmp sgt i32 %x22, %x28
	br i1 %x29, label %block6, label %block7

block6:
	%x30 = alloca i32
	%x31 = load i32, i32* %x4
	%x32 = add i32 0, 1
	%x33 = add i32 %x31, %x32
	%x34 = load i32*, i32* * %x2
	%x35 = getelementptr i32, i32* %x34, i32 %x33
	%x36 = load i32, i32* %x35
	store i32 %x36, i32* %x30
	%x37 = load i32, i32* %x4
	%x38 = add i32 0, 1
	%x39 = add i32 %x37, %x38
	%x40 = load i32*, i32* * %x2
	%x41 = getelementptr i32, i32* %x40, i32 %x39
	%x42 = load i32, i32* %x4
	%x43 = load i32*, i32* * %x2
	%x44 = getelementptr i32, i32* %x43, i32 %x42
	%x45 = load i32, i32* %x44
	store i32 %x45, i32* %x41
	%x46 = load i32, i32* %x4
	%x47 = load i32*, i32* * %x2
	%x48 = getelementptr i32, i32* %x47, i32 %x46
	%x49 = load i32, i32* %x30
	store i32 %x49, i32* %x48
	br label %block7

block7:
	%x50 = load i32, i32* %x4
	%x51 = add i32 0, 1
	%x52 = add i32 %x50, %x51
	store i32 %x52, i32* %x4
	br label %block3

block5:
	%x53 = load i32, i32* %x3
	%x54 = add i32 0, 1
	%x55 = add i32 %x53, %x54
	store i32 %x55, i32* %x3
	br label %block0

block2:
	%x56 = add i32 0, 0
	ret i32 %x56
}
define dso_local i32 @insertsort(i32* %x0){
	%x2 = alloca i32*
	store i32* %x0, i32* * %x2
	%x3 = alloca i32
	%x4 = add i32 0, 1
	store i32 %x4, i32* %x3
	br label %block0

block0:
	%x5 = load i32, i32* %x3
	%x6 = load i32, i32* @n
	%x7 = icmp slt i32 %x5, %x6
	br i1 %x7, label %block1, label %block2

block1:
	%x8 = alloca i32
	%x9 = load i32, i32* %x3
	%x10 = load i32*, i32* * %x2
	%x11 = getelementptr i32, i32* %x10, i32 %x9
	%x12 = load i32, i32* %x11
	store i32 %x12, i32* %x8
	%x13 = alloca i32
	%x14 = load i32, i32* %x3
	%x15 = add i32 0, 1
	%x16 = sub i32 %x14, %x15
	store i32 %x16, i32* %x13
	br label %block3

block3:
	%x17 = load i32, i32* %x13
	%x18 = add i32 0, 1
	%x19 = sub i32 0, %x18
	%x20 = icmp sgt i32 %x17, %x19
	br i1 %x20, label %block6, label %block5

block6:
	%x21 = load i32, i32* %x8
	%x22 = load i32, i32* %x13
	%x23 = load i32*, i32* * %x2
	%x24 = getelementptr i32, i32* %x23, i32 %x22
	%x25 = load i32, i32* %x24
	%x26 = icmp slt i32 %x21, %x25
	br i1 %x26, label %block4, label %block5

block4:
	%x27 = load i32, i32* %x13
	%x28 = add i32 0, 1
	%x29 = add i32 %x27, %x28
	%x30 = load i32*, i32* * %x2
	%x31 = getelementptr i32, i32* %x30, i32 %x29
	%x32 = load i32, i32* %x13
	%x33 = load i32*, i32* * %x2
	%x34 = getelementptr i32, i32* %x33, i32 %x32
	%x35 = load i32, i32* %x34
	store i32 %x35, i32* %x31
	%x36 = load i32, i32* %x13
	%x37 = add i32 0, 1
	%x38 = sub i32 %x36, %x37
	store i32 %x38, i32* %x13
	br label %block3

block5:
	%x39 = load i32, i32* %x13
	%x40 = add i32 0, 1
	%x41 = add i32 %x39, %x40
	%x42 = load i32*, i32* * %x2
	%x43 = getelementptr i32, i32* %x42, i32 %x41
	%x44 = load i32, i32* %x8
	store i32 %x44, i32* %x43
	%x45 = load i32, i32* %x3
	%x46 = add i32 0, 1
	%x47 = add i32 %x45, %x46
	store i32 %x47, i32* %x3
	br label %block0

block2:
	%x48 = add i32 0, 0
	ret i32 %x48
}
define dso_local i32 @QuickSort(i32* %x0, i32 %x1, i32 %x2){
	%x4 = alloca i32*
	store i32* %x0, i32* * %x4
	%x5 = alloca i32
	store i32 %x2, i32* %x5
	%x6 = alloca i32
	store i32 %x1, i32* %x6
	%x7 = load i32, i32* %x6
	%x8 = load i32, i32* %x5
	%x9 = icmp slt i32 %x7, %x8
	br i1 %x9, label %block0, label %block1

block0:
	%x10 = alloca i32
	%x11 = load i32, i32* %x6
	store i32 %x11, i32* %x10
	%x12 = alloca i32
	%x13 = load i32, i32* %x5
	store i32 %x13, i32* %x12
	%x14 = alloca i32
	%x15 = load i32, i32* %x6
	%x16 = load i32*, i32* * %x4
	%x17 = getelementptr i32, i32* %x16, i32 %x15
	%x18 = load i32, i32* %x17
	store i32 %x18, i32* %x14
	br label %block2

block2:
	%x19 = load i32, i32* %x10
	%x20 = load i32, i32* %x12
	%x21 = icmp slt i32 %x19, %x20
	br i1 %x21, label %block3, label %block4

block3:
	br label %block5

block5:
	%x22 = load i32, i32* %x10
	%x23 = load i32, i32* %x12
	%x24 = icmp slt i32 %x22, %x23
	br i1 %x24, label %block8, label %block7

block8:
	%x25 = load i32, i32* %x12
	%x26 = load i32*, i32* * %x4
	%x27 = getelementptr i32, i32* %x26, i32 %x25
	%x28 = load i32, i32* %x27
	%x29 = load i32, i32* %x14
	%x30 = add i32 0, 1
	%x31 = sub i32 %x29, %x30
	%x32 = icmp sgt i32 %x28, %x31
	br i1 %x32, label %block6, label %block7

block6:
	%x33 = load i32, i32* %x12
	%x34 = add i32 0, 1
	%x35 = sub i32 %x33, %x34
	store i32 %x35, i32* %x12
	br label %block5

block7:
	%x36 = load i32, i32* %x10
	%x37 = load i32, i32* %x12
	%x38 = icmp slt i32 %x36, %x37
	br i1 %x38, label %block9, label %block10

block9:
	%x39 = load i32, i32* %x10
	%x40 = load i32*, i32* * %x4
	%x41 = getelementptr i32, i32* %x40, i32 %x39
	%x42 = load i32, i32* %x12
	%x43 = load i32*, i32* * %x4
	%x44 = getelementptr i32, i32* %x43, i32 %x42
	%x45 = load i32, i32* %x44
	store i32 %x45, i32* %x41
	%x46 = load i32, i32* %x10
	%x47 = add i32 0, 1
	%x48 = add i32 %x46, %x47
	store i32 %x48, i32* %x10
	br label %block10

block10:
	br label %block11

block11:
	%x49 = load i32, i32* %x10
	%x50 = load i32, i32* %x12
	%x51 = icmp slt i32 %x49, %x50
	br i1 %x51, label %block14, label %block13

block14:
	%x52 = load i32, i32* %x10
	%x53 = load i32*, i32* * %x4
	%x54 = getelementptr i32, i32* %x53, i32 %x52
	%x55 = load i32, i32* %x54
	%x56 = load i32, i32* %x14
	%x57 = icmp slt i32 %x55, %x56
	br i1 %x57, label %block12, label %block13

block12:
	%x58 = load i32, i32* %x10
	%x59 = add i32 0, 1
	%x60 = add i32 %x58, %x59
	store i32 %x60, i32* %x10
	br label %block11

block13:
	%x61 = load i32, i32* %x10
	%x62 = load i32, i32* %x12
	%x63 = icmp slt i32 %x61, %x62
	br i1 %x63, label %block15, label %block16

block15:
	%x64 = load i32, i32* %x12
	%x65 = load i32*, i32* * %x4
	%x66 = getelementptr i32, i32* %x65, i32 %x64
	%x67 = load i32, i32* %x10
	%x68 = load i32*, i32* * %x4
	%x69 = getelementptr i32, i32* %x68, i32 %x67
	%x70 = load i32, i32* %x69
	store i32 %x70, i32* %x66
	%x71 = load i32, i32* %x12
	%x72 = add i32 0, 1
	%x73 = sub i32 %x71, %x72
	store i32 %x73, i32* %x12
	br label %block16

block16:
	br label %block2

block4:
	%x74 = load i32, i32* %x10
	%x75 = load i32*, i32* * %x4
	%x76 = getelementptr i32, i32* %x75, i32 %x74
	%x77 = load i32, i32* %x14
	store i32 %x77, i32* %x76
	%x78 = alloca i32
	%x79 = load i32, i32* %x10
	%x80 = add i32 0, 1
	%x81 = sub i32 %x79, %x80
	store i32 %x81, i32* %x78
	%x82 = load i32*, i32* * %x4
	%x83 = load i32, i32* %x6
	%x84 = load i32, i32* %x78
	%x85 = call i32 @QuickSort(i32* %x82, i32 %x83, i32 %x84)
	store i32 %x85, i32* %x78
	%x86 = load i32, i32* %x10
	%x87 = add i32 0, 1
	%x88 = add i32 %x86, %x87
	store i32 %x88, i32* %x78
	%x89 = load i32*, i32* * %x4
	%x90 = load i32, i32* %x78
	%x91 = load i32, i32* %x5
	%x92 = call i32 @QuickSort(i32* %x89, i32 %x90, i32 %x91)
	store i32 %x92, i32* %x78
	br label %block1

block1:
	%x93 = add i32 0, 0
	ret i32 %x93
}
define dso_local i32 @getMid(i32* %x0){
	%x2 = alloca i32*
	store i32* %x0, i32* * %x2
	%x3 = alloca i32
	%x4 = load i32, i32* @n
	%x5 = add i32 0, 2
	%x6 = srem i32 %x4, %x5
	%x7 = add i32 0, 0
	%x8 = icmp eq i32 %x6, %x7
	br i1 %x8, label %block0, label %block1

block0:
	%x9 = load i32, i32* @n
	%x10 = add i32 0, 2
	%x11 = sdiv i32 %x9, %x10
	store i32 %x11, i32* %x3
	%x12 = load i32, i32* %x3
	%x13 = load i32*, i32* * %x2
	%x14 = getelementptr i32, i32* %x13, i32 %x12
	%x15 = load i32, i32* %x14
	%x16 = load i32, i32* %x3
	%x17 = add i32 0, 1
	%x18 = sub i32 %x16, %x17
	%x19 = load i32*, i32* * %x2
	%x20 = getelementptr i32, i32* %x19, i32 %x18
	%x21 = load i32, i32* %x20
	%x22 = add i32 %x15, %x21
	%x23 = add i32 0, 2
	%x24 = sdiv i32 %x22, %x23
	ret i32 %x24

block1:
	%x25 = load i32, i32* @n
	%x26 = add i32 0, 2
	%x27 = sdiv i32 %x25, %x26
	store i32 %x27, i32* %x3
	%x28 = load i32, i32* %x3
	%x29 = load i32*, i32* * %x2
	%x30 = getelementptr i32, i32* %x29, i32 %x28
	%x31 = load i32, i32* %x30
	ret i32 %x31
}
define dso_local i32 @getMost(i32* %x0){
	%x2 = alloca i32*
	store i32* %x0, i32* * %x2
	%x3 = add i32 0, 1000
	%x4 = alloca [1000 x i32]
	%x5 = alloca i32
	%x6 = add i32 0, 0
	store i32 %x6, i32* %x5
	br label %block0

block0:
	%x7 = load i32, i32* %x5
	%x8 = add i32 0, 1000
	%x9 = icmp slt i32 %x7, %x8
	br i1 %x9, label %block1, label %block2

block1:
	%x10 = load i32, i32* %x5
	%x11 = getelementptr  [1000 x i32],  [1000 x i32]* %x4, i32 0, i32 %x10
	%x12 = add i32 0, 0
	store i32 %x12, i32* %x11
	%x13 = load i32, i32* %x5
	%x14 = add i32 0, 1
	%x15 = add i32 %x13, %x14
	store i32 %x15, i32* %x5
	br label %block0

block2:
	%x16 = add i32 0, 0
	store i32 %x16, i32* %x5
	%x17 = alloca i32
	%x18 = alloca i32
	%x19 = add i32 0, 0
	store i32 %x19, i32* %x17
	br label %block3

block3:
	%x20 = load i32, i32* %x5
	%x21 = load i32, i32* @n
	%x22 = icmp slt i32 %x20, %x21
	br i1 %x22, label %block4, label %block5

block4:
	%x23 = alloca i32
	%x24 = load i32, i32* %x5
	%x25 = load i32*, i32* * %x2
	%x26 = getelementptr i32, i32* %x25, i32 %x24
	%x27 = load i32, i32* %x26
	store i32 %x27, i32* %x23
	%x28 = load i32, i32* %x23
	%x29 = getelementptr  [1000 x i32],  [1000 x i32]* %x4, i32 0, i32 %x28
	%x30 = load i32, i32* %x23
	%x31 = getelementptr  [1000 x i32],  [1000 x i32]* %x4, i32 0, i32 %x30
	%x32 = load i32, i32* %x31
	%x33 = add i32 0, 1
	%x34 = add i32 %x32, %x33
	store i32 %x34, i32* %x29
	%x35 = load i32, i32* %x23
	%x36 = getelementptr  [1000 x i32],  [1000 x i32]* %x4, i32 0, i32 %x35
	%x37 = load i32, i32* %x36
	%x38 = load i32, i32* %x17
	%x39 = icmp sgt i32 %x37, %x38
	br i1 %x39, label %block6, label %block7

block6:
	%x40 = load i32, i32* %x23
	%x41 = getelementptr  [1000 x i32],  [1000 x i32]* %x4, i32 0, i32 %x40
	%x42 = load i32, i32* %x41
	store i32 %x42, i32* %x17
	%x43 = load i32, i32* %x23
	store i32 %x43, i32* %x18
	br label %block7

block7:
	%x44 = load i32, i32* %x5
	%x45 = add i32 0, 1
	%x46 = add i32 %x44, %x45
	store i32 %x46, i32* %x5
	br label %block3

block5:
	%x47 = load i32, i32* %x18
	ret i32 %x47
}
define dso_local i32 @revert(i32* %x0){
	%x2 = alloca i32*
	store i32* %x0, i32* * %x2
	%x3 = alloca i32
	%x4 = alloca i32
	%x5 = alloca i32
	%x6 = add i32 0, 0
	store i32 %x6, i32* %x4
	%x7 = add i32 0, 0
	store i32 %x7, i32* %x5
	br label %block0

block0:
	%x8 = load i32, i32* %x4
	%x9 = load i32, i32* %x5
	%x10 = icmp slt i32 %x8, %x9
	br i1 %x10, label %block1, label %block2

block1:
	%x11 = load i32, i32* %x4
	%x12 = load i32*, i32* * %x2
	%x13 = getelementptr i32, i32* %x12, i32 %x11
	%x14 = load i32, i32* %x13
	store i32 %x14, i32* %x3
	%x15 = load i32, i32* %x4
	%x16 = load i32*, i32* * %x2
	%x17 = getelementptr i32, i32* %x16, i32 %x15
	%x18 = load i32, i32* %x5
	%x19 = load i32*, i32* * %x2
	%x20 = getelementptr i32, i32* %x19, i32 %x18
	%x21 = load i32, i32* %x20
	store i32 %x21, i32* %x17
	%x22 = load i32, i32* %x5
	%x23 = load i32*, i32* * %x2
	%x24 = getelementptr i32, i32* %x23, i32 %x22
	%x25 = load i32, i32* %x3
	store i32 %x25, i32* %x24
	%x26 = load i32, i32* %x4
	%x27 = add i32 0, 1
	%x28 = add i32 %x26, %x27
	store i32 %x28, i32* %x4
	%x29 = load i32, i32* %x5
	%x30 = add i32 0, 1
	%x31 = sub i32 %x29, %x30
	store i32 %x31, i32* %x5
	br label %block0

block2:
	%x32 = add i32 0, 0
	ret i32 %x32
}
define dso_local i32 @arrCopy(i32* %x0, i32* %x1){
	%x3 = alloca i32*
	store i32* %x0, i32* * %x3
	%x4 = alloca i32*
	store i32* %x1, i32* * %x4
	%x5 = alloca i32
	%x6 = add i32 0, 0
	store i32 %x6, i32* %x5
	br label %block0

block0:
	%x7 = load i32, i32* %x5
	%x8 = load i32, i32* @n
	%x9 = icmp slt i32 %x7, %x8
	br i1 %x9, label %block1, label %block2

block1:
	%x10 = load i32, i32* %x5
	%x11 = load i32*, i32* * %x4
	%x12 = getelementptr i32, i32* %x11, i32 %x10
	%x13 = load i32, i32* %x5
	%x14 = load i32*, i32* * %x3
	%x15 = getelementptr i32, i32* %x14, i32 %x13
	%x16 = load i32, i32* %x15
	store i32 %x16, i32* %x12
	%x17 = load i32, i32* %x5
	%x18 = add i32 0, 1
	%x19 = add i32 %x17, %x18
	store i32 %x19, i32* %x5
	br label %block0

block2:
	%x20 = add i32 0, 0
	ret i32 %x20
}
define dso_local i32 @calSum(i32* %x0, i32 %x1){
	%x3 = alloca i32*
	store i32* %x0, i32* * %x3
	%x4 = alloca i32
	store i32 %x1, i32* %x4
	%x5 = alloca i32
	%x6 = add i32 0, 0
	store i32 %x6, i32* %x5
	%x7 = alloca i32
	%x8 = add i32 0, 0
	store i32 %x8, i32* %x7
	br label %block0

block0:
	%x9 = load i32, i32* %x7
	%x10 = load i32, i32* @n
	%x11 = icmp slt i32 %x9, %x10
	br i1 %x11, label %block1, label %block2

block1:
	%x12 = load i32, i32* %x5
	%x13 = load i32, i32* %x7
	%x14 = load i32*, i32* * %x3
	%x15 = getelementptr i32, i32* %x14, i32 %x13
	%x16 = load i32, i32* %x15
	%x17 = add i32 %x12, %x16
	store i32 %x17, i32* %x5
	%x18 = load i32, i32* %x7
	%x19 = load i32, i32* %x4
	%x20 = srem i32 %x18, %x19
	%x21 = load i32, i32* %x4
	%x22 = add i32 0, 1
	%x23 = sub i32 %x21, %x22
	%x24 = icmp ne i32 %x20, %x23
	br i1 %x24, label %block3, label %block4

block3:
	%x25 = load i32, i32* %x7
	%x26 = load i32*, i32* * %x3
	%x27 = getelementptr i32, i32* %x26, i32 %x25
	%x28 = add i32 0, 0
	store i32 %x28, i32* %x27
	br label %block5

block4:
	%x29 = load i32, i32* %x7
	%x30 = load i32*, i32* * %x3
	%x31 = getelementptr i32, i32* %x30, i32 %x29
	%x32 = load i32, i32* %x5
	store i32 %x32, i32* %x31
	%x33 = add i32 0, 0
	store i32 %x33, i32* %x5
	br label %block5

block5:
	%x34 = load i32, i32* %x7
	%x35 = add i32 0, 1
	%x36 = add i32 %x34, %x35
	store i32 %x36, i32* %x7
	br label %block0

block2:
	%x37 = add i32 0, 0
	ret i32 %x37
}
define dso_local i32 @avgPooling(i32* %x0, i32 %x1){
	%x3 = alloca i32*
	store i32* %x0, i32* * %x3
	%x4 = alloca i32
	store i32 %x1, i32* %x4
	%x5 = alloca i32
	%x6 = alloca i32
	%x7 = add i32 0, 0
	store i32 %x7, i32* %x6
	%x8 = add i32 0, 0
	store i32 %x8, i32* %x5
	%x9 = alloca i32
	br label %block0

block0:
	%x10 = load i32, i32* %x6
	%x11 = load i32, i32* @n
	%x12 = icmp slt i32 %x10, %x11
	br i1 %x12, label %block1, label %block2

block1:
	%x13 = load i32, i32* %x6
	%x14 = load i32, i32* %x4
	%x15 = add i32 0, 1
	%x16 = sub i32 %x14, %x15
	%x17 = icmp slt i32 %x13, %x16
	br i1 %x17, label %block3, label %block4

block3:
	%x18 = load i32, i32* %x5
	%x19 = load i32, i32* %x6
	%x20 = load i32*, i32* * %x3
	%x21 = getelementptr i32, i32* %x20, i32 %x19
	%x22 = load i32, i32* %x21
	%x23 = add i32 %x18, %x22
	store i32 %x23, i32* %x5
	br label %block5

block4:
	%x24 = load i32, i32* %x6
	%x25 = load i32, i32* %x4
	%x26 = add i32 0, 1
	%x27 = sub i32 %x25, %x26
	%x28 = icmp eq i32 %x24, %x27
	br i1 %x28, label %block6, label %block7

block6:
	%x29 = add i32 0, 0
	%x30 = load i32*, i32* * %x3
	%x31 = getelementptr i32, i32* %x30, i32 %x29
	%x32 = load i32, i32* %x31
	store i32 %x32, i32* %x9
	%x33 = add i32 0, 0
	%x34 = load i32*, i32* * %x3
	%x35 = getelementptr i32, i32* %x34, i32 %x33
	%x36 = load i32, i32* %x5
	%x37 = load i32, i32* %x4
	%x38 = sdiv i32 %x36, %x37
	store i32 %x38, i32* %x35
	br label %block5

block7:
	%x39 = load i32, i32* %x5
	%x40 = load i32, i32* %x6
	%x41 = load i32*, i32* * %x3
	%x42 = getelementptr i32, i32* %x41, i32 %x40
	%x43 = load i32, i32* %x42
	%x44 = add i32 %x39, %x43
	%x45 = load i32, i32* %x9
	%x46 = sub i32 %x44, %x45
	store i32 %x46, i32* %x5
	%x47 = load i32, i32* %x6
	%x48 = load i32, i32* %x4
	%x49 = sub i32 %x47, %x48
	%x50 = add i32 0, 1
	%x51 = add i32 %x49, %x50
	%x52 = load i32*, i32* * %x3
	%x53 = getelementptr i32, i32* %x52, i32 %x51
	%x54 = load i32, i32* %x53
	store i32 %x54, i32* %x9
	%x55 = load i32, i32* %x6
	%x56 = load i32, i32* %x4
	%x57 = sub i32 %x55, %x56
	%x58 = add i32 0, 1
	%x59 = add i32 %x57, %x58
	%x60 = load i32*, i32* * %x3
	%x61 = getelementptr i32, i32* %x60, i32 %x59
	%x62 = load i32, i32* %x5
	%x63 = load i32, i32* %x4
	%x64 = sdiv i32 %x62, %x63
	store i32 %x64, i32* %x61
	br label %block5

block5:
	%x65 = load i32, i32* %x6
	%x66 = add i32 0, 1
	%x67 = add i32 %x65, %x66
	store i32 %x67, i32* %x6
	br label %block0

block2:
	%x68 = load i32, i32* @n
	%x69 = load i32, i32* %x4
	%x70 = sub i32 %x68, %x69
	%x71 = add i32 0, 1
	%x72 = add i32 %x70, %x71
	store i32 %x72, i32* %x6
	br label %block8

block8:
	%x73 = load i32, i32* %x6
	%x74 = load i32, i32* @n
	%x75 = icmp slt i32 %x73, %x74
	br i1 %x75, label %block9, label %block10

block9:
	%x76 = load i32, i32* %x6
	%x77 = load i32*, i32* * %x3
	%x78 = getelementptr i32, i32* %x77, i32 %x76
	%x79 = add i32 0, 0
	store i32 %x79, i32* %x78
	%x80 = load i32, i32* %x6
	%x81 = add i32 0, 1
	%x82 = add i32 %x80, %x81
	store i32 %x82, i32* %x6
	br label %block8

block10:
	%x83 = add i32 0, 0
	ret i32 %x83
}
define dso_local i32 @main(){
	%x1 = add i32 0, 32
	store i32 %x1, i32* @n
	%x2 = add i32 0, 32
	%x3 = alloca [32 x i32]
	%x4 = add i32 0, 32
	%x5 = alloca [32 x i32]
	%x6 = add i32 0, 0
	%x7 = getelementptr  [32 x i32],  [32 x i32]* %x3, i32 0, i32 %x6
	%x8 = add i32 0, 7
	store i32 %x8, i32* %x7
	%x9 = add i32 0, 1
	%x10 = getelementptr  [32 x i32],  [32 x i32]* %x3, i32 0, i32 %x9
	%x11 = add i32 0, 23
	store i32 %x11, i32* %x10
	%x12 = add i32 0, 2
	%x13 = getelementptr  [32 x i32],  [32 x i32]* %x3, i32 0, i32 %x12
	%x14 = add i32 0, 89
	store i32 %x14, i32* %x13
	%x15 = add i32 0, 3
	%x16 = getelementptr  [32 x i32],  [32 x i32]* %x3, i32 0, i32 %x15
	%x17 = add i32 0, 26
	store i32 %x17, i32* %x16
	%x18 = add i32 0, 4
	%x19 = getelementptr  [32 x i32],  [32 x i32]* %x3, i32 0, i32 %x18
	%x20 = add i32 0, 282
	store i32 %x20, i32* %x19
	%x21 = add i32 0, 5
	%x22 = getelementptr  [32 x i32],  [32 x i32]* %x3, i32 0, i32 %x21
	%x23 = add i32 0, 254
	store i32 %x23, i32* %x22
	%x24 = add i32 0, 6
	%x25 = getelementptr  [32 x i32],  [32 x i32]* %x3, i32 0, i32 %x24
	%x26 = add i32 0, 27
	store i32 %x26, i32* %x25
	%x27 = add i32 0, 7
	%x28 = getelementptr  [32 x i32],  [32 x i32]* %x3, i32 0, i32 %x27
	%x29 = add i32 0, 5
	store i32 %x29, i32* %x28
	%x30 = add i32 0, 8
	%x31 = getelementptr  [32 x i32],  [32 x i32]* %x3, i32 0, i32 %x30
	%x32 = add i32 0, 83
	store i32 %x32, i32* %x31
	%x33 = add i32 0, 9
	%x34 = getelementptr  [32 x i32],  [32 x i32]* %x3, i32 0, i32 %x33
	%x35 = add i32 0, 273
	store i32 %x35, i32* %x34
	%x36 = add i32 0, 10
	%x37 = getelementptr  [32 x i32],  [32 x i32]* %x3, i32 0, i32 %x36
	%x38 = add i32 0, 574
	store i32 %x38, i32* %x37
	%x39 = add i32 0, 11
	%x40 = getelementptr  [32 x i32],  [32 x i32]* %x3, i32 0, i32 %x39
	%x41 = add i32 0, 905
	store i32 %x41, i32* %x40
	%x42 = add i32 0, 12
	%x43 = getelementptr  [32 x i32],  [32 x i32]* %x3, i32 0, i32 %x42
	%x44 = add i32 0, 354
	store i32 %x44, i32* %x43
	%x45 = add i32 0, 13
	%x46 = getelementptr  [32 x i32],  [32 x i32]* %x3, i32 0, i32 %x45
	%x47 = add i32 0, 657
	store i32 %x47, i32* %x46
	%x48 = add i32 0, 14
	%x49 = getelementptr  [32 x i32],  [32 x i32]* %x3, i32 0, i32 %x48
	%x50 = add i32 0, 935
	store i32 %x50, i32* %x49
	%x51 = add i32 0, 15
	%x52 = getelementptr  [32 x i32],  [32 x i32]* %x3, i32 0, i32 %x51
	%x53 = add i32 0, 264
	store i32 %x53, i32* %x52
	%x54 = add i32 0, 16
	%x55 = getelementptr  [32 x i32],  [32 x i32]* %x3, i32 0, i32 %x54
	%x56 = add i32 0, 639
	store i32 %x56, i32* %x55
	%x57 = add i32 0, 17
	%x58 = getelementptr  [32 x i32],  [32 x i32]* %x3, i32 0, i32 %x57
	%x59 = add i32 0, 459
	store i32 %x59, i32* %x58
	%x60 = add i32 0, 18
	%x61 = getelementptr  [32 x i32],  [32 x i32]* %x3, i32 0, i32 %x60
	%x62 = add i32 0, 29
	store i32 %x62, i32* %x61
	%x63 = add i32 0, 19
	%x64 = getelementptr  [32 x i32],  [32 x i32]* %x3, i32 0, i32 %x63
	%x65 = add i32 0, 68
	store i32 %x65, i32* %x64
	%x66 = add i32 0, 20
	%x67 = getelementptr  [32 x i32],  [32 x i32]* %x3, i32 0, i32 %x66
	%x68 = add i32 0, 929
	store i32 %x68, i32* %x67
	%x69 = add i32 0, 21
	%x70 = getelementptr  [32 x i32],  [32 x i32]* %x3, i32 0, i32 %x69
	%x71 = add i32 0, 756
	store i32 %x71, i32* %x70
	%x72 = add i32 0, 22
	%x73 = getelementptr  [32 x i32],  [32 x i32]* %x3, i32 0, i32 %x72
	%x74 = add i32 0, 452
	store i32 %x74, i32* %x73
	%x75 = add i32 0, 23
	%x76 = getelementptr  [32 x i32],  [32 x i32]* %x3, i32 0, i32 %x75
	%x77 = add i32 0, 279
	store i32 %x77, i32* %x76
	%x78 = add i32 0, 24
	%x79 = getelementptr  [32 x i32],  [32 x i32]* %x3, i32 0, i32 %x78
	%x80 = add i32 0, 58
	store i32 %x80, i32* %x79
	%x81 = add i32 0, 25
	%x82 = getelementptr  [32 x i32],  [32 x i32]* %x3, i32 0, i32 %x81
	%x83 = add i32 0, 87
	store i32 %x83, i32* %x82
	%x84 = add i32 0, 26
	%x85 = getelementptr  [32 x i32],  [32 x i32]* %x3, i32 0, i32 %x84
	%x86 = add i32 0, 96
	store i32 %x86, i32* %x85
	%x87 = add i32 0, 27
	%x88 = getelementptr  [32 x i32],  [32 x i32]* %x3, i32 0, i32 %x87
	%x89 = add i32 0, 36
	store i32 %x89, i32* %x88
	%x90 = add i32 0, 28
	%x91 = getelementptr  [32 x i32],  [32 x i32]* %x3, i32 0, i32 %x90
	%x92 = add i32 0, 39
	store i32 %x92, i32* %x91
	%x93 = add i32 0, 29
	%x94 = getelementptr  [32 x i32],  [32 x i32]* %x3, i32 0, i32 %x93
	%x95 = add i32 0, 28
	store i32 %x95, i32* %x94
	%x96 = add i32 0, 30
	%x97 = getelementptr  [32 x i32],  [32 x i32]* %x3, i32 0, i32 %x96
	%x98 = add i32 0, 1
	store i32 %x98, i32* %x97
	%x99 = add i32 0, 31
	%x100 = getelementptr  [32 x i32],  [32 x i32]* %x3, i32 0, i32 %x99
	%x101 = add i32 0, 290
	store i32 %x101, i32* %x100
	%x102 = alloca i32
	%x103 = getelementptr  [32 x i32],  [32 x i32]* %x3, i32 0, i32 0
	%x104 = getelementptr  [32 x i32],  [32 x i32]* %x5, i32 0, i32 0
	%x105 = call i32 @arrCopy(i32* %x103, i32* %x104)
	store i32 %x105, i32* %x102
	%x106 = getelementptr  [32 x i32],  [32 x i32]* %x5, i32 0, i32 0
	%x107 = call i32 @revert(i32* %x106)
	store i32 %x107, i32* %x102
	%x108 = alloca i32
	%x109 = add i32 0, 0
	store i32 %x109, i32* %x108
	br label %block0

block0:
	%x110 = load i32, i32* %x108
	%x111 = add i32 0, 32
	%x112 = icmp slt i32 %x110, %x111
	br i1 %x112, label %block1, label %block2

block1:
	%x113 = load i32, i32* %x108
	%x114 = getelementptr  [32 x i32],  [32 x i32]* %x5, i32 0, i32 %x113
	%x115 = load i32, i32* %x114
	store i32 %x115, i32* %x102
	%x116 = load i32, i32* %x102
	call void @putint(i32 %x116)
	%x117 = load i32, i32* %x108
	%x118 = add i32 0, 1
	%x119 = add i32 %x117, %x118
	store i32 %x119, i32* %x108
	br label %block0

block2:
	%x120 = getelementptr  [32 x i32],  [32 x i32]* %x5, i32 0, i32 0
	%x121 = call i32 @bubblesort(i32* %x120)
	store i32 %x121, i32* %x102
	%x122 = add i32 0, 0
	store i32 %x122, i32* %x108
	br label %block3

block3:
	%x123 = load i32, i32* %x108
	%x124 = add i32 0, 32
	%x125 = icmp slt i32 %x123, %x124
	br i1 %x125, label %block4, label %block5

block4:
	%x126 = load i32, i32* %x108
	%x127 = getelementptr  [32 x i32],  [32 x i32]* %x5, i32 0, i32 %x126
	%x128 = load i32, i32* %x127
	store i32 %x128, i32* %x102
	%x129 = load i32, i32* %x102
	call void @putint(i32 %x129)
	%x130 = load i32, i32* %x108
	%x131 = add i32 0, 1
	%x132 = add i32 %x130, %x131
	store i32 %x132, i32* %x108
	br label %block3

block5:
	%x133 = getelementptr  [32 x i32],  [32 x i32]* %x5, i32 0, i32 0
	%x134 = call i32 @getMid(i32* %x133)
	store i32 %x134, i32* %x102
	%x135 = load i32, i32* %x102
	call void @putint(i32 %x135)
	%x136 = getelementptr  [32 x i32],  [32 x i32]* %x5, i32 0, i32 0
	%x137 = call i32 @getMost(i32* %x136)
	store i32 %x137, i32* %x102
	%x138 = load i32, i32* %x102
	call void @putint(i32 %x138)
	%x139 = getelementptr  [32 x i32],  [32 x i32]* %x3, i32 0, i32 0
	%x140 = getelementptr  [32 x i32],  [32 x i32]* %x5, i32 0, i32 0
	%x141 = call i32 @arrCopy(i32* %x139, i32* %x140)
	store i32 %x141, i32* %x102
	%x142 = getelementptr  [32 x i32],  [32 x i32]* %x5, i32 0, i32 0
	%x143 = call i32 @bubblesort(i32* %x142)
	store i32 %x143, i32* %x102
	%x144 = add i32 0, 0
	store i32 %x144, i32* %x108
	br label %block6

block6:
	%x145 = load i32, i32* %x108
	%x146 = add i32 0, 32
	%x147 = icmp slt i32 %x145, %x146
	br i1 %x147, label %block7, label %block8

block7:
	%x148 = load i32, i32* %x108
	%x149 = getelementptr  [32 x i32],  [32 x i32]* %x5, i32 0, i32 %x148
	%x150 = load i32, i32* %x149
	store i32 %x150, i32* %x102
	%x151 = load i32, i32* %x102
	call void @putint(i32 %x151)
	%x152 = load i32, i32* %x108
	%x153 = add i32 0, 1
	%x154 = add i32 %x152, %x153
	store i32 %x154, i32* %x108
	br label %block6

block8:
	%x155 = getelementptr  [32 x i32],  [32 x i32]* %x3, i32 0, i32 0
	%x156 = getelementptr  [32 x i32],  [32 x i32]* %x5, i32 0, i32 0
	%x157 = call i32 @arrCopy(i32* %x155, i32* %x156)
	store i32 %x157, i32* %x102
	%x158 = getelementptr  [32 x i32],  [32 x i32]* %x5, i32 0, i32 0
	%x159 = call i32 @insertsort(i32* %x158)
	store i32 %x159, i32* %x102
	%x160 = add i32 0, 0
	store i32 %x160, i32* %x108
	br label %block9

block9:
	%x161 = load i32, i32* %x108
	%x162 = add i32 0, 32
	%x163 = icmp slt i32 %x161, %x162
	br i1 %x163, label %block10, label %block11

block10:
	%x164 = load i32, i32* %x108
	%x165 = getelementptr  [32 x i32],  [32 x i32]* %x5, i32 0, i32 %x164
	%x166 = load i32, i32* %x165
	store i32 %x166, i32* %x102
	%x167 = load i32, i32* %x102
	call void @putint(i32 %x167)
	%x168 = load i32, i32* %x108
	%x169 = add i32 0, 1
	%x170 = add i32 %x168, %x169
	store i32 %x170, i32* %x108
	br label %block9

block11:
	%x171 = getelementptr  [32 x i32],  [32 x i32]* %x3, i32 0, i32 0
	%x172 = getelementptr  [32 x i32],  [32 x i32]* %x5, i32 0, i32 0
	%x173 = call i32 @arrCopy(i32* %x171, i32* %x172)
	store i32 %x173, i32* %x102
	%x174 = add i32 0, 0
	store i32 %x174, i32* %x108
	%x175 = add i32 0, 31
	store i32 %x175, i32* %x102
	%x176 = getelementptr  [32 x i32],  [32 x i32]* %x5, i32 0, i32 0
	%x177 = load i32, i32* %x108
	%x178 = load i32, i32* %x102
	%x179 = call i32 @QuickSort(i32* %x176, i32 %x177, i32 %x178)
	store i32 %x179, i32* %x102
	br label %block12

block12:
	%x180 = load i32, i32* %x108
	%x181 = add i32 0, 32
	%x182 = icmp slt i32 %x180, %x181
	br i1 %x182, label %block13, label %block14

block13:
	%x183 = load i32, i32* %x108
	%x184 = getelementptr  [32 x i32],  [32 x i32]* %x5, i32 0, i32 %x183
	%x185 = load i32, i32* %x184
	store i32 %x185, i32* %x102
	%x186 = load i32, i32* %x102
	call void @putint(i32 %x186)
	%x187 = load i32, i32* %x108
	%x188 = add i32 0, 1
	%x189 = add i32 %x187, %x188
	store i32 %x189, i32* %x108
	br label %block12

block14:
	%x190 = getelementptr  [32 x i32],  [32 x i32]* %x3, i32 0, i32 0
	%x191 = getelementptr  [32 x i32],  [32 x i32]* %x5, i32 0, i32 0
	%x192 = call i32 @arrCopy(i32* %x190, i32* %x191)
	store i32 %x192, i32* %x102
	%x193 = getelementptr  [32 x i32],  [32 x i32]* %x5, i32 0, i32 0
	%x194 = add i32 0, 4
	%x195 = call i32 @calSum(i32* %x193, i32 %x194)
	store i32 %x195, i32* %x102
	%x196 = add i32 0, 0
	store i32 %x196, i32* %x108
	br label %block15

block15:
	%x197 = load i32, i32* %x108
	%x198 = add i32 0, 32
	%x199 = icmp slt i32 %x197, %x198
	br i1 %x199, label %block16, label %block17

block16:
	%x200 = load i32, i32* %x108
	%x201 = getelementptr  [32 x i32],  [32 x i32]* %x5, i32 0, i32 %x200
	%x202 = load i32, i32* %x201
	store i32 %x202, i32* %x102
	%x203 = load i32, i32* %x102
	call void @putint(i32 %x203)
	%x204 = load i32, i32* %x108
	%x205 = add i32 0, 1
	%x206 = add i32 %x204, %x205
	store i32 %x206, i32* %x108
	br label %block15

block17:
	%x207 = getelementptr  [32 x i32],  [32 x i32]* %x3, i32 0, i32 0
	%x208 = getelementptr  [32 x i32],  [32 x i32]* %x5, i32 0, i32 0
	%x209 = call i32 @arrCopy(i32* %x207, i32* %x208)
	store i32 %x209, i32* %x102
	%x210 = getelementptr  [32 x i32],  [32 x i32]* %x5, i32 0, i32 0
	%x211 = add i32 0, 3
	%x212 = call i32 @avgPooling(i32* %x210, i32 %x211)
	store i32 %x212, i32* %x102
	%x213 = add i32 0, 0
	store i32 %x213, i32* %x108
	br label %block18

block18:
	%x214 = load i32, i32* %x108
	%x215 = add i32 0, 32
	%x216 = icmp slt i32 %x214, %x215
	br i1 %x216, label %block19, label %block20

block19:
	%x217 = load i32, i32* %x108
	%x218 = getelementptr  [32 x i32],  [32 x i32]* %x5, i32 0, i32 %x217
	%x219 = load i32, i32* %x218
	store i32 %x219, i32* %x102
	%x220 = load i32, i32* %x102
	call void @putint(i32 %x220)
	%x221 = load i32, i32* %x108
	%x222 = add i32 0, 1
	%x223 = add i32 %x221, %x222
	store i32 %x223, i32* %x108
	br label %block18

block20:
	%x224 = add i32 0, 0
	ret i32 %x224
}