package uk.ac.tees.mad.stayfinder.ui.screens.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import uk.ac.tees.mad.stayfinder.data.model.Destination
import uk.ac.tees.mad.stayfinder.ui.theme.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    onDestClick: (Destination) -> Unit,
    destinations: List<Destination>,
    isActive: Boolean,
    onActiveChange: (Boolean) -> Unit ,
    isLoading : Boolean = false ,
) {

        SearchBar(
            modifier = Modifier
                .padding(horizontal = Dimens.MediumPadding),
            query = query,
            onQueryChange = onQueryChange,
            onSearch = {
                onSearch()
                onActiveChange(false)
            },
            active = isActive,
            onActiveChange = onActiveChange,
            placeholder = {
                if (!isActive) {
                    Text(text = "Search")
                }
            },
            colors = SearchBarDefaults.colors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "search"
                )
            } ,
            trailingIcon = {
                if(isLoading){
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(Dimens.SpacerSmall)
            ) {
                items(
                    destinations,
                    key = { destination -> destination.id }
                ) { destination ->
                    DestinationItem(
                        destination = destination,
                        onDestClick = onDestClick
                    )
                }
            }
        }
    }


@Composable
fun DestinationItem(destination: Destination ,onDestClick:( Destination) -> Unit){

    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable{
            onDestClick(destination)
        }) {
        Text(
            text = destination.name ,
            modifier = Modifier.padding(Dimens.SmallPadding),
            maxLines = 1 ,
            overflow = TextOverflow.Ellipsis
        )
        Divider()
    }
}