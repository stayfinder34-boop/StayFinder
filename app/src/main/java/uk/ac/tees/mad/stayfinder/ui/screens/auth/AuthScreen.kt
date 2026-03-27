package uk.ac.tees.mad.stayfinder.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import uk.ac.tees.mad.stayfinder.ui.screens.auth.component.AuthToggle
import uk.ac.tees.mad.stayfinder.ui.screens.auth.component.ConfirmPasswordField
import uk.ac.tees.mad.stayfinder.ui.screens.auth.component.EmailField
import uk.ac.tees.mad.stayfinder.ui.screens.auth.component.HeaderSection
import uk.ac.tees.mad.stayfinder.ui.screens.auth.component.PasswordField
import uk.ac.tees.mad.stayfinder.ui.theme.Dimens
import uk.ac.tees.mad.stayfinder.utils.AuthMode

@Composable
fun AuthScreen(viewModel: AuthViewModel = viewModel() ,
               onNavigateHome:()-> Unit){
    val uiState by viewModel.authUiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.navigateToHome) {
        if(uiState.navigateToHome){
            onNavigateHome()
        }
    }

    AuthScreenContent(
        uiState = uiState,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onConfirmPasswordChange = viewModel ::onConfirmPasswordChange,
        onSubmitClick = viewModel::onSubmitButtonClick ,
        onSelectAuthMode = viewModel::onAuthModeChange
    )
}


@Composable
fun AuthScreenContent(uiState: AuthUiState ,
                      onEmailChange:(String)-> Unit ,
                      onPasswordChange:(String)-> Unit,
                      onConfirmPasswordChange:(String)-> Unit,
                      onSubmitClick:()-> Unit ,
                      onSelectAuthMode:(AuthMode)-> Unit){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .navigationBarsPadding()
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(
            modifier = Modifier.height(Dimens.SpacerLarge)
        )
        HeaderSection()
        Spacer(
            modifier = Modifier.height(Dimens.SpacerLarge)
        )

        AuthToggle(
            onSelectMode = onSelectAuthMode,
            selectedMode = uiState.selectedMode
        )

        Spacer(
            modifier = Modifier.height(
                Dimens.SpacerSmall
            )
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = Dimens.ScreenPadding)
                .verticalScroll(rememberScrollState())
        ) {
            EmailField(
                email = uiState.email,
                onValueChange =  onEmailChange
            )
            Spacer(
                modifier = Modifier.height(Dimens.SpacerSmall)
            )
            PasswordField(
                password = uiState.password ,
                onValueChange = onPasswordChange
            )
            Spacer(
                modifier = Modifier.height(Dimens.SpacerLarge)
            )
            if(uiState.selectedMode == AuthMode.SIGNUP){
                ConfirmPasswordField(
                    confirmPassword = uiState.confirmPassword,
                    onValueChange = onConfirmPasswordChange
                )
                Spacer(
                    modifier = Modifier.height(Dimens.SpacerLarge)
                )
            }
            Button(
                onClick = onSubmitClick ,
                enabled =  !uiState.isLoading && uiState.canSubmit,
                modifier = Modifier.fillMaxWidth().height(Dimens.ButtonHeight),
                shape = RoundedCornerShape(Dimens.ButtonCornerRadius)
            ){
                if(uiState.isLoading){
                    Box(
                        contentAlignment = Alignment.Center
                    ){
                        CircularProgressIndicator()
                    }
                }else{
                    Text(
                        text = uiState.selectedMode.name
                    )
                }
            }
        }

        uiState.error?.let {
            Text(
                text = it ,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AuthScreenPreview(){
   AuthScreenContent(
       uiState = AuthUiState(),
       {},
       {},
       {},
       {},
       {}
   )
}