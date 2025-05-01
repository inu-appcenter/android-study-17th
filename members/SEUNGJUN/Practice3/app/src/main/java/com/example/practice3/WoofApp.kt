package com.example.practice3

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WoofApp(){
    // TODO: Scaffold를 사용하여 전체 앱 구조를 만들기
    // TODO: Scaffold의 content 부분에 LazyColumn을 구현
    // TODO: LazyColumn 내부에 items() 함수를 사용하여 dogs 리스트의 각 항목을 DogItem으로 변환
    Scaffold(
        topBar = { WoofTopAppBar() }
    ) { innerPadding ->
        LazyColumn (modifier = Modifier.padding(innerPadding)){
            items(dogs) { dog ->
                DogItem(
                    dog = dog,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}

@Composable
fun DogItem(
    dog: Dog,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    // TODO: animateColorAsState를 사용하여 expanded 상태에 따라 배경색이 변하도록 구현
    // expanded가 true면 MaterialTheme.colorScheme.tertiaryContainer 색상을,
    // false면 MaterialTheme.colorScheme.primaryContainer 색상을 사용
    val color by animateColorAsState(
        if (expanded)
            MaterialTheme.colorScheme.tertiaryContainer
        else
            MaterialTheme.colorScheme.primaryContainer
    )
    Card(
        modifier = modifier
    ) {
        // TODO: Column을 구현하고 animateContentSize로 애니메이션 효과를 추가
        // 힌트: Column의 modifier에 .animateContentSize()와 .background(color)를 추가
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize()
                .background(Color(0xFF84EB99))
        ) {

            // TODO: Row를 구현하여 강아지 정보를 가로로 배치
            // Row 안에는 다음 요소들이 포함되어야 합니다:
            Row()
            {
                // 1. DogIcon(dog.imageResourceId)
                DogIcon(dogIcon = dog.imageResourceId)
                // 2. DogInformation(dog.name, dog.age)
                DogInformation(dogName = dog.name, dogAge = dog.age)
                // 3. Spacer(Modifier.weight(1f))
                Spacer(Modifier.weight(1f))
                // 4. DogItemButton(expanded, onClick = { expanded = !expanded })
                DogItemButton(expanded = expanded, onClick = { expanded = !expanded })

            }
            // TODO: expanded가 true일 때만 DogHobby를 표시하는 조건문을 추가
            // 힌트: if (expanded) { DogHobby(...) }
            if (expanded){
                DogHobby(
                    dogHobby = dog.hobbies,
                    modifier = Modifier
                        .padding(25.dp)
                )
            }
        }
    }
}


@Composable
private fun DogItemButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        androidx.compose.material3.Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = stringResource(R.string.expand_button_content_description),
            tint = Color.Black
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WoofTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.image_size))
                        .padding(dimensionResource(R.dimen.padding_small)),
                    painter = painterResource(R.drawable.ic_woof_logo),

                    // Content Description is not needed here - image is decorative, and setting a
                    // null content description allows accessibility services to skip this element
                    // during navigation.

                    contentDescription = null
                )
                Text(
                    text = stringResource(R.string.woof_app_name),
                    style = MaterialTheme.typography.displayLarge
                )
            }
        },
        modifier = modifier
    )
}

/**
 * Composable that displays a photo of a dog.
 *
 * @param dogIcon is the resource ID for the image of the dog
 * @param modifier modifiers to set to this composable
 */
@Composable
fun DogIcon(
    @DrawableRes dogIcon: Int,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier
            .size(dimensionResource(R.dimen.image_size))
            .padding(dimensionResource(R.dimen.padding_small))
            .clip(MaterialTheme.shapes.small),
        contentScale = ContentScale.Crop,
        painter = painterResource(dogIcon),

        // Content Description is not needed here - image is decorative, and setting a null content
        // description allows accessibility services to skip this element during navigation.

        contentDescription = null
    )
}

/**
 * Composable that displays a dog's name and age.
 *
 * @param dogName is the resource ID for the string of the dog's name
 * @param dogAge is the Int that represents the dog's age
 * @param modifier modifiers to set to this composable
 */
@Composable
fun DogInformation(
    @StringRes dogName: Int,
    dogAge: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(dogName),
            fontSize = 20.sp,
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
        )
        Text(
            text = stringResource(R.string.years_old, dogAge),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

/**
 * Composable that displays a dog's hobbies.
 *
 * @param dogHobby is the resource ID for the text string of the hobby to display
 * @param modifier modifiers to set to this composable
 */
@Composable
fun DogHobby(
    @StringRes dogHobby: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.about),
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            text = stringResource(dogHobby),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

/**
 * Composable that displays what the UI of the app looks like in light theme in the design tab.
 */
@Preview
@Composable
fun WoofPreview() {
    WoofApp()
}