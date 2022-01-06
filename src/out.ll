declare void @putarray(i32,i32*)
@golbal = dso_local global [100 x i32] [ i32 1, i32 2, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0, i32 0]
@x = dso_local global i32 0
@y = dso_local global i32 0
@z = dso_local global i32 0
define dso_local void @f(i32* %x0, i32 %x1){
	%x3 = alloca i32*
	store i32* %x0, i32* * %x3
	%x4 = alloca i32
	store i32 %x1, i32* %x4
	%x5 = add i32 0, 3
	%x6 = add i32 0, 3
	%x7 = add i32 0, 3
	%x8 = alloca [3 x [3 x [3 x i32]]]
	%x9 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 0, i32 0, i32 0
	%x10 = add i32 0, 1
	store i32 %x10, i32* %x9
	%x11 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 0, i32 0, i32 1
	%x12 = add i32 0, 2
	store i32 %x12, i32* %x11
	%x13 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 0, i32 0, i32 2
	%x14 = add i32 0, 3
	store i32 %x14, i32* %x13
	%x15 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 0, i32 1, i32 0
	%x16 = add i32 0, 4
	store i32 %x16, i32* %x15
	%x17 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 0, i32 1, i32 1
	%x18 = add i32 0, 5
	store i32 %x18, i32* %x17
	%x19 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 0, i32 1, i32 2
	%x20 = add i32 0, 6
	store i32 %x20, i32* %x19
	%x21 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 0, i32 2, i32 0
	%x22 = add i32 0, 7
	store i32 %x22, i32* %x21
	%x23 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 0, i32 2, i32 1
	%x24 = add i32 0, 8
	store i32 %x24, i32* %x23
	%x25 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 0, i32 2, i32 2
	%x26 = add i32 0, 9
	store i32 %x26, i32* %x25
	%x27 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 1, i32 0, i32 0
	%x28 = add i32 0, 1
	store i32 %x28, i32* %x27
	%x29 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 1, i32 0, i32 1
	%x30 = add i32 0, 2
	store i32 %x30, i32* %x29
	%x31 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 1, i32 0, i32 2
	%x32 = add i32 0, 3
	store i32 %x32, i32* %x31
	%x33 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 1, i32 1, i32 0
	%x34 = add i32 0, 4
	store i32 %x34, i32* %x33
	%x35 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 1, i32 1, i32 1
	%x36 = add i32 0, 5
	store i32 %x36, i32* %x35
	%x37 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 1, i32 1, i32 2
	%x38 = add i32 0, 6
	store i32 %x38, i32* %x37
	%x39 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 1, i32 2, i32 0
	%x40 = add i32 0, 7
	store i32 %x40, i32* %x39
	%x41 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 1, i32 2, i32 1
	%x42 = add i32 0, 8
	store i32 %x42, i32* %x41
	%x43 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 1, i32 2, i32 2
	%x44 = add i32 0, 9
	store i32 %x44, i32* %x43
	%x45 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 2, i32 0, i32 0
	%x46 = add i32 0, 1
	store i32 %x46, i32* %x45
	%x47 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 2, i32 0, i32 1
	%x48 = add i32 0, 2
	store i32 %x48, i32* %x47
	%x49 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 2, i32 0, i32 2
	%x50 = add i32 0, 3
	store i32 %x50, i32* %x49
	%x51 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 2, i32 1, i32 0
	%x52 = add i32 0, 4
	store i32 %x52, i32* %x51
	%x53 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 2, i32 1, i32 1
	%x54 = add i32 0, 5
	store i32 %x54, i32* %x53
	%x55 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 2, i32 1, i32 2
	%x56 = add i32 0, 6
	store i32 %x56, i32* %x55
	%x57 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 2, i32 2, i32 0
	%x58 = add i32 0, 7
	store i32 %x58, i32* %x57
	%x59 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 2, i32 2, i32 1
	%x60 = add i32 0, 8
	store i32 %x60, i32* %x59
	%x61 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 2, i32 2, i32 2
	%x62 = add i32 0, 9
	store i32 %x62, i32* %x61
	%x63 = add i32 0, 3
	%x64 = add i32 0, 3
	%x65 = add i32 0, 3
	%x66 = alloca [3 x [3 x [3 x i32]]]
	%x67 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 0, i32 0, i32 0
	%x68 = add i32 0, 1
	store i32 %x68, i32* %x67
	%x69 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 0, i32 0, i32 1
	%x70 = add i32 0, 2
	store i32 %x70, i32* %x69
	%x71 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 0, i32 0, i32 2
	%x72 = add i32 0, 3
	store i32 %x72, i32* %x71
	%x73 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 0, i32 1, i32 0
	%x74 = add i32 0, 4
	store i32 %x74, i32* %x73
	%x75 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 0, i32 1, i32 1
	%x76 = add i32 0, 5
	store i32 %x76, i32* %x75
	%x77 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 0, i32 1, i32 2
	%x78 = add i32 0, 6
	store i32 %x78, i32* %x77
	%x79 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 0, i32 2, i32 0
	%x80 = add i32 0, 7
	store i32 %x80, i32* %x79
	%x81 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 0, i32 2, i32 1
	%x82 = add i32 0, 8
	store i32 %x82, i32* %x81
	%x83 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 0, i32 2, i32 2
	%x84 = add i32 0, 9
	store i32 %x84, i32* %x83
	%x85 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 1, i32 0, i32 0
	%x86 = add i32 0, 1
	store i32 %x86, i32* %x85
	%x87 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 1, i32 0, i32 1
	%x88 = add i32 0, 2
	store i32 %x88, i32* %x87
	%x89 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 1, i32 0, i32 2
	%x90 = add i32 0, 3
	store i32 %x90, i32* %x89
	%x91 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 1, i32 1, i32 0
	%x92 = add i32 0, 4
	store i32 %x92, i32* %x91
	%x93 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 1, i32 1, i32 1
	%x94 = add i32 0, 5
	store i32 %x94, i32* %x93
	%x95 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 1, i32 1, i32 2
	%x96 = add i32 0, 6
	store i32 %x96, i32* %x95
	%x97 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 1, i32 2, i32 0
	%x98 = add i32 0, 7
	store i32 %x98, i32* %x97
	%x99 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 1, i32 2, i32 1
	%x100 = add i32 0, 8
	store i32 %x100, i32* %x99
	%x101 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 1, i32 2, i32 2
	%x102 = add i32 0, 9
	store i32 %x102, i32* %x101
	%x103 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 2, i32 0, i32 0
	%x104 = add i32 0, 1
	store i32 %x104, i32* %x103
	%x105 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 2, i32 0, i32 1
	%x106 = add i32 0, 2
	store i32 %x106, i32* %x105
	%x107 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 2, i32 0, i32 2
	%x108 = add i32 0, 3
	store i32 %x108, i32* %x107
	%x109 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 2, i32 1, i32 0
	%x110 = add i32 0, 4
	store i32 %x110, i32* %x109
	%x111 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 2, i32 1, i32 1
	%x112 = add i32 0, 5
	store i32 %x112, i32* %x111
	%x113 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 2, i32 1, i32 2
	%x114 = add i32 0, 6
	store i32 %x114, i32* %x113
	%x115 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 2, i32 2, i32 0
	%x116 = add i32 0, 7
	store i32 %x116, i32* %x115
	%x117 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 2, i32 2, i32 1
	%x118 = add i32 0, 8
	store i32 %x118, i32* %x117
	%x119 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 2, i32 2, i32 2
	%x120 = add i32 0, 9
	store i32 %x120, i32* %x119
	%x121 = add i32 0, 3
	%x122 = add i32 0, 3
	%x123 = add i32 0, 3
	%x124 = alloca [3 x [3 x [3 x i32]]]
	%x125 = alloca i32
	%x126 = alloca i32
	%x127 = alloca i32
	%x128 = alloca i32
	%x129 = add i32 0, 0
	store i32 %x129, i32* %x128
	%x130 = alloca i32
	%x131 = add i32 0, 0
	store i32 %x131, i32* %x130
	%x132 = alloca i32
	%x133 = add i32 0, 0
	store i32 %x133, i32* %x132
	br label %block0

block0:
	%x134 = load i32, i32* %x128
	%x135 = add i32 0, 3
	%x136 = icmp slt i32 %x134, %x135
	br i1 %x136, label %block1, label %block2

block1:
	%x137 = add i32 0, 0
	store i32 %x137, i32* %x130
	br label %block3

block3:
	%x138 = load i32, i32* %x130
	%x139 = add i32 0, 3
	%x140 = icmp slt i32 %x138, %x139
	br i1 %x140, label %block4, label %block5

block4:
	%x141 = add i32 0, 0
	store i32 %x141, i32* %x132
	br label %block6

block6:
	%x142 = load i32, i32* %x132
	%x143 = add i32 0, 3
	%x144 = icmp slt i32 %x142, %x143
	br i1 %x144, label %block7, label %block8

block7:
	%x145 = load i32, i32* %x128
	%x146 = load i32, i32* %x130
	%x147 = load i32, i32* %x132
	%x148 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x124, i32 0, i32 %x145, i32 %x146, i32 %x147
	%x149 = load i32, i32* %x128
	%x150 = load i32, i32* %x130
	%x151 = load i32, i32* %x132
	%x152 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x8, i32 0, i32 %x149, i32 %x150, i32 %x151
	%x153 = load i32, i32* %x152
	%x154 = load i32, i32* %x128
	%x155 = load i32, i32* %x130
	%x156 = load i32, i32* %x132
	%x157 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x66, i32 0, i32 %x154, i32 %x155, i32 %x156
	%x158 = load i32, i32* %x157
	%x159 = add i32 %x153, %x158
	store i32 %x159, i32* %x148
	%x160 = load i32, i32* %x132
	%x161 = add i32 0, 1
	%x162 = add i32 %x160, %x161
	store i32 %x162, i32* %x132
	br label %block6

block8:
	%x163 = load i32, i32* %x130
	%x164 = add i32 0, 1
	%x165 = add i32 %x163, %x164
	store i32 %x165, i32* %x130
	br label %block3

block5:
	%x166 = load i32, i32* %x128
	%x167 = add i32 0, 1
	%x168 = add i32 %x166, %x167
	store i32 %x168, i32* %x128
	br label %block0

block2:
	%x169 = add i32 0, 3
	%x170 = add i32 0, 0
	%x171 = add i32 0, 0
	%x172 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x124, i32 0, i32 %x170, i32 %x171, i32 0
	call void @putarray(i32 %x169, i32* %x172)
	%x173 = add i32 0, 3
	%x174 = add i32 0, 0
	%x175 = add i32 0, 1
	%x176 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x124, i32 0, i32 %x174, i32 %x175, i32 0
	call void @putarray(i32 %x173, i32* %x176)
	%x177 = add i32 0, 3
	%x178 = add i32 0, 0
	%x179 = add i32 0, 2
	%x180 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x124, i32 0, i32 %x178, i32 %x179, i32 0
	call void @putarray(i32 %x177, i32* %x180)
	%x181 = add i32 0, 3
	%x182 = add i32 0, 1
	%x183 = add i32 0, 0
	%x184 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x124, i32 0, i32 %x182, i32 %x183, i32 0
	call void @putarray(i32 %x181, i32* %x184)
	%x185 = add i32 0, 3
	%x186 = add i32 0, 1
	%x187 = add i32 0, 1
	%x188 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x124, i32 0, i32 %x186, i32 %x187, i32 0
	call void @putarray(i32 %x185, i32* %x188)
	%x189 = add i32 0, 3
	%x190 = add i32 0, 1
	%x191 = add i32 0, 2
	%x192 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x124, i32 0, i32 %x190, i32 %x191, i32 0
	call void @putarray(i32 %x189, i32* %x192)
	%x193 = add i32 0, 3
	%x194 = add i32 0, 2
	%x195 = add i32 0, 0
	%x196 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x124, i32 0, i32 %x194, i32 %x195, i32 0
	call void @putarray(i32 %x193, i32* %x196)
	%x197 = add i32 0, 3
	%x198 = add i32 0, 2
	%x199 = add i32 0, 1
	%x200 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x124, i32 0, i32 %x198, i32 %x199, i32 0
	call void @putarray(i32 %x197, i32* %x200)
	%x201 = add i32 0, 3
	%x202 = add i32 0, 2
	%x203 = add i32 0, 2
	%x204 = getelementptr  [3 x [3 x [3 x i32]]],  [3 x [3 x [3 x i32]]]* %x124, i32 0, i32 %x202, i32 %x203, i32 0
	call void @putarray(i32 %x201, i32* %x204)
	ret void
}
define dso_local i32 @main(){
	%x1 = getelementptr  [100 x i32],  [100 x i32]*@golbal, i32 0, i32 0
	%x2 = add i32 0, 16
	call void @f(i32* %x1, i32 %x2)
	%x3 = add i32 0, 0
	ret i32 %x3
}declare void @putint(i32)
declare i32 @getint()
@maxn = dso_local global i32 18
@mod = dso_local global i32 1000000007
@dp = dso_local global [18 x [18 x [18 x [18 x [18 x [7 x i32]]]]]] zeroinitializer
@list = dso_local global [200 x i32] zeroinitializer
define dso_local i32 @equal(i32 %x0, i32 %x1){
	%x3 = alloca i32
	store i32 %x0, i32* %x3
	%x4 = alloca i32
	store i32 %x1, i32* %x4
	%x5 = load i32, i32* %x3
	%x6 = load i32, i32* %x4
	%x7 = icmp eq i32 %x5, %x6
	br i1 %x7, label %block0, label %block1

block0:
	%x8 = add i32 0, 1
	ret i32 %x8

block1:
	%x9 = add i32 0, 0
	ret i32 %x9
}
define dso_local i32 @dfs(i32 %x0, i32 %x1, i32 %x2, i32 %x3, i32 %x4, i32 %x5){
	%x7 = alloca i32
	store i32 %x0, i32* %x7
	%x8 = alloca i32
	store i32 %x1, i32* %x8
	%x9 = alloca i32
	store i32 %x2, i32* %x9
	%x10 = alloca i32
	store i32 %x3, i32* %x10
	%x11 = alloca i32
	store i32 %x5, i32* %x11
	%x12 = alloca i32
	store i32 %x4, i32* %x12
	%x13 = load i32, i32* %x7
	%x14 = load i32, i32* %x8
	%x15 = load i32, i32* %x9
	%x16 = load i32, i32* %x10
	%x17 = load i32, i32* %x12
	%x18 = load i32, i32* %x11
	%x19 = getelementptr  [18 x [18 x [18 x [18 x [18 x [7 x i32]]]]]],  [18 x [18 x [18 x [18 x [18 x [7 x i32]]]]]]* @dp, i32 0, i32 %x13, i32 %x14, i32 %x15, i32 %x16, i32 %x17, i32 %x18
	%x20 = load i32, i32* %x19
	%x21 = add i32 0, 1
	%x22 = sub i32 0, %x21
	%x23 = icmp ne i32 %x20, %x22
	br i1 %x23, label %block0, label %block1

block0:
	%x24 = load i32, i32* %x7
	%x25 = load i32, i32* %x8
	%x26 = load i32, i32* %x9
	%x27 = load i32, i32* %x10
	%x28 = load i32, i32* %x12
	%x29 = load i32, i32* %x11
	%x30 = getelementptr  [18 x [18 x [18 x [18 x [18 x [7 x i32]]]]]],  [18 x [18 x [18 x [18 x [18 x [7 x i32]]]]]]* @dp, i32 0, i32 %x24, i32 %x25, i32 %x26, i32 %x27, i32 %x28, i32 %x29
	%x31 = load i32, i32* %x30
	ret i32 %x31

block1:
	%x32 = load i32, i32* %x7
	%x33 = load i32, i32* %x8
	%x34 = add i32 %x32, %x33
	%x35 = load i32, i32* %x9
	%x36 = add i32 %x34, %x35
	%x37 = load i32, i32* %x10
	%x38 = add i32 %x36, %x37
	%x39 = load i32, i32* %x12
	%x40 = add i32 %x38, %x39
	%x41 = add i32 0, 0
	%x42 = icmp eq i32 %x40, %x41
	br i1 %x42, label %block2, label %block3

block2:
	%x43 = add i32 0, 1
	ret i32 %x43

block3:
	%x44 = alloca i32
	%x45 = add i32 0, 0
	store i32 %x45, i32* %x44
	%x46 = load i32, i32* %x7
	%x47 = icmp ne i32 %x46, 0
	br i1 %x47, label %block4, label %block5

block4:
	%x48 = load i32, i32* %x44
	%x49 = load i32, i32* %x7
	%x50 = load i32, i32* %x11
	%x51 = add i32 0, 2
	%x52 = call i32 @equal(i32 %x50, i32 %x51)
	%x53 = sub i32 %x49, %x52
	%x54 = load i32, i32* %x7
	%x55 = add i32 0, 1
	%x56 = sub i32 %x54, %x55
	%x57 = load i32, i32* %x8
	%x58 = load i32, i32* %x9
	%x59 = load i32, i32* %x10
	%x60 = load i32, i32* %x12
	%x61 = add i32 0, 1
	%x62 = call i32 @dfs(i32 %x56, i32 %x57, i32 %x58, i32 %x59, i32 %x60, i32 %x61)
	%x63 = mul i32 %x53, %x62
	%x64 = add i32 %x48, %x63
	%x65 = load i32, i32* @mod
	%x66 = srem i32 %x64, %x65
	store i32 %x66, i32* %x44
	br label %block5

block5:
	%x67 = load i32, i32* %x8
	%x68 = icmp ne i32 %x67, 0
	br i1 %x68, label %block6, label %block7

block6:
	%x69 = load i32, i32* %x44
	%x70 = load i32, i32* %x8
	%x71 = load i32, i32* %x11
	%x72 = add i32 0, 3
	%x73 = call i32 @equal(i32 %x71, i32 %x72)
	%x74 = sub i32 %x70, %x73
	%x75 = load i32, i32* %x7
	%x76 = add i32 0, 1
	%x77 = add i32 %x75, %x76
	%x78 = load i32, i32* %x8
	%x79 = add i32 0, 1
	%x80 = sub i32 %x78, %x79
	%x81 = load i32, i32* %x9
	%x82 = load i32, i32* %x10
	%x83 = load i32, i32* %x12
	%x84 = add i32 0, 2
	%x85 = call i32 @dfs(i32 %x77, i32 %x80, i32 %x81, i32 %x82, i32 %x83, i32 %x84)
	%x86 = mul i32 %x74, %x85
	%x87 = add i32 %x69, %x86
	%x88 = load i32, i32* @mod
	%x89 = srem i32 %x87, %x88
	store i32 %x89, i32* %x44
	br label %block7

block7:
	%x90 = load i32, i32* %x9
	%x91 = icmp ne i32 %x90, 0
	br i1 %x91, label %block8, label %block9

block8:
	%x92 = load i32, i32* %x44
	%x93 = load i32, i32* %x9
	%x94 = load i32, i32* %x11
	%x95 = add i32 0, 4
	%x96 = call i32 @equal(i32 %x94, i32 %x95)
	%x97 = sub i32 %x93, %x96
	%x98 = load i32, i32* %x7
	%x99 = load i32, i32* %x8
	%x100 = add i32 0, 1
	%x101 = add i32 %x99, %x100
	%x102 = load i32, i32* %x9
	%x103 = add i32 0, 1
	%x104 = sub i32 %x102, %x103
	%x105 = load i32, i32* %x10
	%x106 = load i32, i32* %x12
	%x107 = add i32 0, 3
	%x108 = call i32 @dfs(i32 %x98, i32 %x101, i32 %x104, i32 %x105, i32 %x106, i32 %x107)
	%x109 = mul i32 %x97, %x108
	%x110 = add i32 %x92, %x109
	%x111 = load i32, i32* @mod
	%x112 = srem i32 %x110, %x111
	store i32 %x112, i32* %x44
	br label %block9

block9:
	%x113 = load i32, i32* %x10
	%x114 = icmp ne i32 %x113, 0
	br i1 %x114, label %block10, label %block11

block10:
	%x115 = load i32, i32* %x44
	%x116 = load i32, i32* %x10
	%x117 = load i32, i32* %x11
	%x118 = add i32 0, 5
	%x119 = call i32 @equal(i32 %x117, i32 %x118)
	%x120 = sub i32 %x116, %x119
	%x121 = load i32, i32* %x7
	%x122 = load i32, i32* %x8
	%x123 = load i32, i32* %x9
	%x124 = add i32 0, 1
	%x125 = add i32 %x123, %x124
	%x126 = load i32, i32* %x10
	%x127 = add i32 0, 1
	%x128 = sub i32 %x126, %x127
	%x129 = load i32, i32* %x12
	%x130 = add i32 0, 4
	%x131 = call i32 @dfs(i32 %x121, i32 %x122, i32 %x125, i32 %x128, i32 %x129, i32 %x130)
	%x132 = mul i32 %x120, %x131
	%x133 = add i32 %x115, %x132
	%x134 = load i32, i32* @mod
	%x135 = srem i32 %x133, %x134
	store i32 %x135, i32* %x44
	br label %block11

block11:
	%x136 = load i32, i32* %x12
	%x137 = icmp ne i32 %x136, 0
	br i1 %x137, label %block12, label %block13

block12:
	%x138 = load i32, i32* %x44
	%x139 = load i32, i32* %x12
	%x140 = load i32, i32* %x7
	%x141 = load i32, i32* %x8
	%x142 = load i32, i32* %x9
	%x143 = load i32, i32* %x10
	%x144 = add i32 0, 1
	%x145 = add i32 %x143, %x144
	%x146 = load i32, i32* %x12
	%x147 = add i32 0, 1
	%x148 = sub i32 %x146, %x147
	%x149 = add i32 0, 5
	%x150 = call i32 @dfs(i32 %x140, i32 %x141, i32 %x142, i32 %x145, i32 %x148, i32 %x149)
	%x151 = mul i32 %x139, %x150
	%x152 = add i32 %x138, %x151
	%x153 = load i32, i32* @mod
	%x154 = srem i32 %x152, %x153
	store i32 %x154, i32* %x44
	br label %block13

block13:
	%x155 = load i32, i32* %x7
	%x156 = load i32, i32* %x8
	%x157 = load i32, i32* %x9
	%x158 = load i32, i32* %x10
	%x159 = load i32, i32* %x12
	%x160 = load i32, i32* %x11
	%x161 = getelementptr  [18 x [18 x [18 x [18 x [18 x [7 x i32]]]]]],  [18 x [18 x [18 x [18 x [18 x [7 x i32]]]]]]* @dp, i32 0, i32 %x155, i32 %x156, i32 %x157, i32 %x158, i32 %x159, i32 %x160
	%x162 = load i32, i32* %x44
	%x163 = load i32, i32* @mod
	%x164 = srem i32 %x162, %x163
	store i32 %x164, i32* %x161
	%x165 = load i32, i32* %x7
	%x166 = load i32, i32* %x8
	%x167 = load i32, i32* %x9
	%x168 = load i32, i32* %x10
	%x169 = load i32, i32* %x12
	%x170 = load i32, i32* %x11
	%x171 = getelementptr  [18 x [18 x [18 x [18 x [18 x [7 x i32]]]]]],  [18 x [18 x [18 x [18 x [18 x [7 x i32]]]]]]* @dp, i32 0, i32 %x165, i32 %x166, i32 %x167, i32 %x168, i32 %x169, i32 %x170
	%x172 = load i32, i32* %x171
	ret i32 %x172
}
@cns = dso_local global [20 x i32] zeroinitializer
define dso_local i32 @main(){
	%x1 = alloca i32
	%x2 = call i32 @getint()
	store i32 %x2, i32* %x1
	%x3 = alloca i32
	%x4 = add i32 0, 0
	store i32 %x4, i32* %x3
	%x5 = alloca i32
	%x6 = add i32 0, 0
	store i32 %x6, i32* %x5
	%x7 = alloca i32
	%x8 = add i32 0, 0
	store i32 %x8, i32* %x7
	%x9 = alloca i32
	%x10 = add i32 0, 0
	store i32 %x10, i32* %x9
	%x11 = alloca i32
	%x12 = add i32 0, 0
	store i32 %x12, i32* %x11
	%x13 = alloca i32
	%x14 = add i32 0, 0
	store i32 %x14, i32* %x13
	br label %block0

block0:
	%x15 = load i32, i32* %x3
	%x16 = load i32, i32* @maxn
	%x17 = icmp slt i32 %x15, %x16
	br i1 %x17, label %block1, label %block2

block1:
	%x18 = add i32 0, 0
	store i32 %x18, i32* %x5
	br label %block3

block3:
	%x19 = load i32, i32* %x5
	%x20 = load i32, i32* @maxn
	%x21 = icmp slt i32 %x19, %x20
	br i1 %x21, label %block4, label %block5

block4:
	%x22 = add i32 0, 0
	store i32 %x22, i32* %x7
	br label %block6

block6:
	%x23 = load i32, i32* %x7
	%x24 = load i32, i32* @maxn
	%x25 = icmp slt i32 %x23, %x24
	br i1 %x25, label %block7, label %block8

block7:
	%x26 = add i32 0, 0
	store i32 %x26, i32* %x9
	br label %block9

block9:
	%x27 = load i32, i32* %x9
	%x28 = load i32, i32* @maxn
	%x29 = icmp slt i32 %x27, %x28
	br i1 %x29, label %block10, label %block11

block10:
	%x30 = add i32 0, 0
	store i32 %x30, i32* %x11
	br label %block12

block12:
	%x31 = load i32, i32* %x11
	%x32 = load i32, i32* @maxn
	%x33 = icmp slt i32 %x31, %x32
	br i1 %x33, label %block13, label %block14

block13:
	%x34 = add i32 0, 0
	store i32 %x34, i32* %x13
	br label %block15

block15:
	%x35 = load i32, i32* %x13
	%x36 = add i32 0, 7
	%x37 = icmp slt i32 %x35, %x36
	br i1 %x37, label %block16, label %block17

block16:
	%x38 = load i32, i32* %x3
	%x39 = load i32, i32* %x5
	%x40 = load i32, i32* %x7
	%x41 = load i32, i32* %x9
	%x42 = load i32, i32* %x11
	%x43 = load i32, i32* %x13
	%x44 = getelementptr  [18 x [18 x [18 x [18 x [18 x [7 x i32]]]]]],  [18 x [18 x [18 x [18 x [18 x [7 x i32]]]]]]* @dp, i32 0, i32 %x38, i32 %x39, i32 %x40, i32 %x41, i32 %x42, i32 %x43
	%x45 = add i32 0, 1
	%x46 = sub i32 0, %x45
	store i32 %x46, i32* %x44
	%x47 = load i32, i32* %x13
	%x48 = add i32 0, 1
	%x49 = add i32 %x47, %x48
	store i32 %x49, i32* %x13
	br label %block15

block17:
	%x50 = load i32, i32* %x11
	%x51 = add i32 0, 1
	%x52 = add i32 %x50, %x51
	store i32 %x52, i32* %x11
	br label %block12

block14:
	%x53 = load i32, i32* %x9
	%x54 = add i32 0, 1
	%x55 = add i32 %x53, %x54
	store i32 %x55, i32* %x9
	br label %block9

block11:
	%x56 = load i32, i32* %x7
	%x57 = add i32 0, 1
	%x58 = add i32 %x56, %x57
	store i32 %x58, i32* %x7
	br label %block6

block8:
	%x59 = load i32, i32* %x5
	%x60 = add i32 0, 1
	%x61 = add i32 %x59, %x60
	store i32 %x61, i32* %x5
	br label %block3

block5:
	%x62 = load i32, i32* %x3
	%x63 = add i32 0, 1
	%x64 = add i32 %x62, %x63
	store i32 %x64, i32* %x3
	br label %block0

block2:
	%x65 = add i32 0, 0
	store i32 %x65, i32* %x3
	br label %block18

block18:
	%x66 = load i32, i32* %x3
	%x67 = load i32, i32* %x1
	%x68 = icmp slt i32 %x66, %x67
	br i1 %x68, label %block19, label %block20

block19:
	%x69 = load i32, i32* %x3
	%x70 = getelementptr  [200 x i32],  [200 x i32]* @list, i32 0, i32 %x69
	%x71 = call i32 @getint()
	store i32 %x71, i32* %x70
	%x72 = load i32, i32* %x3
	%x73 = getelementptr  [200 x i32],  [200 x i32]* @list, i32 0, i32 %x72
	%x74 = load i32, i32* %x73
	%x75 = getelementptr  [20 x i32],  [20 x i32]* @cns, i32 0, i32 %x74
	%x76 = load i32, i32* %x3
	%x77 = getelementptr  [200 x i32],  [200 x i32]* @list, i32 0, i32 %x76
	%x78 = load i32, i32* %x77
	%x79 = getelementptr  [20 x i32],  [20 x i32]* @cns, i32 0, i32 %x78
	%x80 = load i32, i32* %x79
	%x81 = add i32 0, 1
	%x82 = add i32 %x80, %x81
	store i32 %x82, i32* %x75
	%x83 = load i32, i32* %x3
	%x84 = add i32 0, 1
	%x85 = add i32 %x83, %x84
	store i32 %x85, i32* %x3
	br label %block18

block20:
	%x86 = alloca i32
	%x87 = add i32 0, 1
	%x88 = getelementptr  [20 x i32],  [20 x i32]* @cns, i32 0, i32 %x87
	%x89 = load i32, i32* %x88
	%x90 = add i32 0, 2
	%x91 = getelementptr  [20 x i32],  [20 x i32]* @cns, i32 0, i32 %x90
	%x92 = load i32, i32* %x91
	%x93 = add i32 0, 3
	%x94 = getelementptr  [20 x i32],  [20 x i32]* @cns, i32 0, i32 %x93
	%x95 = load i32, i32* %x94
	%x96 = add i32 0, 4
	%x97 = getelementptr  [20 x i32],  [20 x i32]* @cns, i32 0, i32 %x96
	%x98 = load i32, i32* %x97
	%x99 = add i32 0, 5
	%x100 = getelementptr  [20 x i32],  [20 x i32]* @cns, i32 0, i32 %x99
	%x101 = load i32, i32* %x100
	%x102 = add i32 0, 0
	%x103 = call i32 @dfs(i32 %x89, i32 %x92, i32 %x95, i32 %x98, i32 %x101, i32 %x102)
	store i32 %x103, i32* %x86
	%x104 = load i32, i32* %x86
	call void @putint(i32 %x104)
	%x105 = add i32 0, 0
	ret i32 %x105
}