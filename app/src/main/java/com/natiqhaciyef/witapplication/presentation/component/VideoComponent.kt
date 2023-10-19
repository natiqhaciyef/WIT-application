package com.natiqhaciyef.voyagersaz.presentation.component

//@Composable
//fun VideoPlayerItem(
//    link: String,
//    videoViewModel: VideoPlayerViewModel = hiltViewModel(),
//    videoModifier: Modifier = Modifier
//        .fillMaxWidth()
//        .height(250.dp),
//    extraAction: @Composable () -> Unit = { }
//) {
//    var lifecycle by remember { mutableStateOf(Lifecycle.Event.ON_CREATE) }
//
//    val lifecycleOwner = LocalLifecycleOwner.current
//    DisposableEffect(lifecycleOwner) {
//        val observer = LifecycleEventObserver { _, event ->
//            lifecycle = event
//        }
//        lifecycleOwner.lifecycle.addObserver(observer)
//        onDispose {
//            lifecycleOwner.lifecycle.removeObserver(observer)
//        }
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(AppExtraLightPurple)
//            .verticalScroll(rememberScrollState()),
//        horizontalAlignment = Alignment.CenterHorizontally,
//    ) {
//        Spacer(modifier = Modifier.height(45.dp))
//        Text(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 20.dp),
//            text = buildAnnotatedString {
//                withStyle(
//                    SpanStyle(
//                        fontSize = 25.sp,
//                        fontFamily = Lobster.lobster,
//                        color = Color.Black,
//                        fontWeight = FontWeight.Bold
//                    )
//                ) {
//                    append("Discover your skills and build your career for the future with, ")
//                }
//                withStyle(
//                    SpanStyle(
//                        fontSize = 25.sp,
//                        fontFamily = Lobster.lobster,
//                        color = AppBlue,
//                        fontWeight = FontWeight.Bold
//                    )
//                ) {
//                    append("Techtive")
//                }
//            }
//        )
//        Spacer(modifier = Modifier.height(20.dp))
//        AndroidView(
//            factory = { context ->
//
//                PlayerView(context).also {
//                    videoViewModel.addVideoLink(link)
//                    it.player = videoViewModel.player
//                }
//            },
//            update = {
//                when (lifecycle) {
//                    Lifecycle.Event.ON_PAUSE -> {
//                        it.onPause()
//                        it.player?.pause()
//                    }
//
//                    Lifecycle.Event.ON_RESUME -> {
//                        it.onResume()
//                    }
//
//                    else -> Unit
//                }
//            },
//            modifier = videoModifier
////                .aspectRatio(16 / 9f)
//        )
//
//        extraAction()
//    }
//}

//@Composable
//fun VideoSelectorAndPlayer(
//    link: MutableState<String>,
//    timePeriod: MutableState<String>,
//    videoPlayerViewModel: VideoPlayerViewModel = hiltViewModel(),
//) {
//    val selectVideoLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.GetContent(),
//        onResult = { result ->
//            result?.let { uri ->
//                link.value = uri.toString()
//                videoPlayerViewModel.addVideoLink(uri.toString())
//                videoPlayerViewModel.getDuration { duration ->
//                    timePeriod.value = "$duration"
//                }
//            }
//        }
//    )
//
//    var lifecycle by remember {
//        mutableStateOf(Lifecycle.Event.ON_CREATE)
//    }
//
//    val lifecycleOwner = LocalLifecycleOwner.current
//    DisposableEffect(lifecycleOwner) {
//        val observer = LifecycleEventObserver { _, event ->
//            lifecycle = event
//        }
//        lifecycleOwner.lifecycle.addObserver(observer)
//        onDispose {
//            lifecycleOwner.lifecycle.removeObserver(observer)
//        }
//    }
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(AppExtraLightPurple)
//            .verticalScroll(rememberScrollState()),
//        horizontalAlignment = Alignment.CenterHorizontally,
//    ) {
//        AndroidView(
//            factory = { context ->
//                PlayerView(context).also {
////                    it.player = courseDetailsViewModel.player
//                }
//            },
//            update = {
//                when (lifecycle) {
//                    Lifecycle.Event.ON_PAUSE -> {
//                        it.onPause()
//                        it.player?.pause()
//                    }
//
//                    Lifecycle.Event.ON_RESUME -> {
//                        it.onResume()
//                    }
//
//                    else -> Unit
//                }
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .aspectRatio(16 / 9f)
//
//        )
//        Spacer(modifier = Modifier.height(30.dp))
//        Button(
//            modifier = Modifier
//                .width(230.dp)
//                .height(55.dp),
//            onClick = {
//                selectVideoLauncher.launch("video/mp4")
//            },
//            colors = ButtonDefaults.buttonColors(
//                backgroundColor = AppYellow
//            ),
//            shape = RoundedCornerShape(8.dp)
//        ) {
//            Box(modifier = Modifier.fillMaxSize()) {
//                Text(
//                    modifier = Modifier.align(Alignment.Center),
//                    text = "Select Video",
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = Color.White,
//                    textAlign = TextAlign.Center
//                )
//            }
//        }
//    }
//}
