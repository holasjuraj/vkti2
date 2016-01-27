#include<vector>
#include<iostream>

using namespace std;

vector<string> strings;
vector<vector<int> > nearest_one, nearest_zero;

bool match(vector<int> match_indexes, int remain, char c) {
    if (remain == 0) return true;
    for (int i = 0; i < strings.size(); i++) {
        string cur_string = strings[i];
        int pom;
        if (match_indexes[i] >= cur_string.size()) return false;
        if (c == '0') {
            pom = nearest_zero[i][match_indexes[i]];
        }
        else {
            pom = nearest_one[i][match_indexes[i]];
        }
        if (pom == -1) return false;
        match_indexes[i] = pom + 1;
    }
    return (match(match_indexes, remain - 1, '0') || match(match_indexes, remain - 1, '1'));
}

// suffixove polia
void compute_nearest(vector<string> strings) {
    for (int i = 0; i < strings.size(); i++) {
        string cur_string = strings[i];
        vector<int> pom0, pom1;
        pom0.resize(cur_string.size());
        pom1.resize(cur_string.size());
        int last_seen_one = -1;
        int last_seen_zero = -1;
        for (int j = cur_string.size() - 1; j >= 0; j--) {
            if (cur_string[j] == '0'){
                last_seen_zero = j;
            }
            else {
                last_seen_one = j;
            }
            pom0[j] = last_seen_zero;
            pom1[j] = last_seen_one;
        }
        nearest_zero.push_back(pom0);
        nearest_one.push_back(pom1);
    }
}

int main(){
    int n,k;
    cin >> n >> k;
    vector<int> match_indexes;
    for (int i = 0; i < n; i++) {
        string pom;
        cin >> pom;
        strings.push_back(pom);
        match_indexes.push_back(0);
    }
    compute_nearest(strings);
    bool ans = match(match_indexes, k, '0') || match(match_indexes, k, '1');

    if (ans) {
        cout << "YES\n";
    }
    else {
        cout << "NO\n";
    }
    return 0;
}
